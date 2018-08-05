package com.example.kwons.cafepay;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    UserService userService;
    private ArrayAdapter adapter;
    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText registerIdText=(EditText)findViewById(R.id.registerIdText);
        Button registerIdCheckButton=(Button)findViewById(R.id.registerIdCheckButton);
        final EditText registerPasswordText=(EditText)findViewById(R.id.registerPasswordText);
        final TextView registerErrorRecheckPassword=(TextView)findViewById(R.id.registerErrorRecheckPassword);
        final EditText registerRecheckPasswordText=(EditText)findViewById(R.id.registerRecheckPasswordText);
        EditText registerUserNameText=(EditText)findViewById(R.id.registerUserNameText);
        RadioGroup registerGenderGroup=(RadioGroup)findViewById(R.id.registerGenderGroup);
        RadioButton genderWoman=(RadioButton)findViewById(R.id.genderWoman);
        RadioButton genderMan=(RadioButton)findViewById(R.id.genderMan);
        EditText registerBirthYear=(EditText)findViewById(R.id.registerBirthYear);
        final EditText registerBirthDate=(EditText)findViewById(R.id.registerBirthDate);

        spinner=(Spinner)findViewById(R.id.registerBirthMonthSpinner);
        adapter=ArrayAdapter.createFromResource(this,R.array.month,android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);

        /**아이디 중복체크*/

        registerIdCheckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**아이디가 공백이면*/
                if (TextUtils.isEmpty(registerIdText.getText().toString())) {
                    AlertDialog.Builder builder=new AlertDialog.Builder(RegisterActivity.this);
                    builder.setMessage("ID는 공백일 수 없습니다.")
                            .setNegativeButton("다시 시도",null)
                            .create()
                            .show();
                }
                /**아이디가 공백이 아니면 중복체크 통신*/
                if(!TextUtils.isEmpty(registerIdText.getText().toString())){
                    userService=RetrofitClient.getClient().create(UserService.class);
                    Call<List<Users>> call=userService.getAllUserList();
                    call.enqueue(new Callback<List<Users>>() {
                        @Override
                        public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                            if(response.isSuccessful()) {
                                String userId=registerIdText.getText().toString();
                                List<Users> users = response.body();
                                for (Users user : users) {
                                    if (user.id.toString().equals(userId))//중복이 발생하면
                                    {
                                        AlertDialog.Builder builder=new AlertDialog.Builder(RegisterActivity.this);
                                        builder.setMessage("중복된 ID가 있습니다. 다시 입력해주세요.")
                                                .setNegativeButton("다시 시도",null)
                                                .create()
                                                .show();
                                        //텍스트초기화
                                        registerIdText.setText("");
                                    }
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Users>> call, Throwable t) {
                            /**요청실패*/
                            Log.i("Register액티비티", "통신에러");
                        }
                    });
                }

            }
            }
        );

        /**비밀번호 재확인*/
        registerRecheckPasswordText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            //비밀번호 재확인에 입력한 내용이 바뀔때마다
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!registerRecheckPasswordText.toString().equals(registerPasswordText.toString())){
                    registerErrorRecheckPassword.setText("비밀번호가 동일하지 않습니다.");
                    registerErrorRecheckPassword.setTextColor(Color.parseColor("#FF0000"));
                }
                if(registerRecheckPasswordText.toString().equals(registerPasswordText.toString())){
                    registerErrorRecheckPassword.setText("비밀번호가 재확인되었습니다.");
                    registerErrorRecheckPassword.setTextColor(Color.parseColor("#6799FF"));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        Button registerButton=(Button)findViewById(R.id.registerButton);


        registerButton.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent registerIntent=new Intent(RegisterActivity.this,LoginActivity.class);
                RegisterActivity.this.startActivity(registerIntent);

            }
        });
    }
}
