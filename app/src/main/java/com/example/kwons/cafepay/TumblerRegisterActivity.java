package com.example.kwons.cafepay;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kwons.cafepay.Service.TumblerRegisterService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.google.zxing.qrcode.QRCodeWriter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//텀블러 인식에 성공하면 성공했다는 Dialog나 Toast를 띄우고 MainActivity로 다시 돌아갑니다.

public class TumblerRegisterActivity extends AppCompatActivity {

    private FloatingActionButton qrcodeButton;
    private Button generateQRcodeButton;
    private TextView tumblerNumberTextView;
    private ImageView imgview;
    private String userId;
    private String serial;
    private TumblerRegisterService tumblerRegisterService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tumbler_register);
        qrcodeButton = findViewById(R.id.qrcode_button);
        generateQRcodeButton = findViewById(R.id.generate_qrcode);
        tumblerNumberTextView = findViewById(R.id.tumbler_number_text_view);
        imgview = findViewById(R.id.image_view);
        qrcodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scanQRCode();
            }
        });
        generateQRcodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDialog();
            }
        });

        Intent fromLoginIntent = getIntent();
        userId =fromLoginIntent.getExtras().getString("userId");

    }


    private void createDialog() {
        final AlertDialog.Builder tumblrDialog = new AlertDialog.Builder(TumblerRegisterActivity.this);

        tumblrDialog.setTitle("QR코드 생성");       // 제목 설정
        tumblrDialog.setMessage("QR코드 값을 입력하세요");   // 내용 설정

        final EditText et = new EditText(TumblerRegisterActivity.this);
        tumblrDialog.setView(et);

        tumblrDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String value = et.getText().toString();
                generateRQCode(value);
                dialog.dismiss();     //닫기
                // Event
            }
        });
        tumblrDialog.show();
    }

    public void scanQRCode() {
        new IntentIntegrator(this).initiateScan();
    }

    public static Bitmap toBitmap(BitMatrix matrix) {
        int height = matrix.getHeight();
        int width = matrix.getWidth();
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                bmp.setPixel(x, y, matrix.get(x, y) ? Color.BLACK : Color.WHITE);
            }
        }
        return bmp;
    }

    public void generateRQCode(String contents) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        try {
            Bitmap bitmap = toBitmap(qrCodeWriter.encode(contents, BarcodeFormat.QR_CODE, 300, 300));
            imgview.setImageBitmap(bitmap);
            SaveBitmapToFileCache(bitmap,  "test.jpg");
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
    private void galleryAddPic(String mCurrentPhotoPath) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

    private void SaveBitmapToFileCache(Bitmap bitmap, String filename) {

        File fileCacheItem = new File(
                Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_PICTURES
                ), filename );
        OutputStream out = null;

        try {
            boolean sucess = fileCacheItem.createNewFile();
            if(sucess) {
                out = new FileOutputStream(fileCacheItem);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
                galleryAddPic(fileCacheItem.getPath());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == IntentIntegrator.REQUEST_CODE) {
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (result == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                serial = result.getContents();

                //Retrofit
                tumblerRegisterService = RetrofitClient.getClient().create(TumblerRegisterService.class);

                JSONObject tumblerInfo = new JSONObject();
                try {
                    tumblerInfo.put("serial", serial);
                    tumblerInfo.put("userId", userId);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Call<JSONObject> callTumblerService = tumblerRegisterService.registerTumblerSerialNumber(tumblerInfo);

                callTumblerService.enqueue(new Callback<JSONObject>() {
                    @Override
                    public void onResponse(Call<JSONObject> call, retrofit2.Response<JSONObject> response) {
                        if (call.isExecuted()) {
                            Toast.makeText(TumblerRegisterActivity.this, "등록성공", Toast.LENGTH_SHORT).show();
                            tumblerNumberTextView.setText(serial);
                        }
                    }

                    @Override
                    public void onFailure(Call<JSONObject> call, Throwable t) {
                        Log.d("Error ", t.getMessage());
                        call.cancel();

                    }
                });
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}
