package com.example.kwons.cafepay;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kwons.cafepay.Service.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    UserService userService;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //자동로그인
        CheckBox autoLogin=(CheckBox)findViewById(R.id.autoLogin);


        //Retrofit
        userService=RetrofitClient.getClient().create(UserService.class);

        TextView registerButton= (TextView) findViewById(R.id.registerButton);
        Button loginButton= (Button) findViewById(R.id.loginButton);

        //회원가입 클릭할때
        registerButton.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent registerIntent=new Intent(LoginActivity.this,RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);

            }
        });

        //로그인버튼 클릭할때
        loginButton.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View view) {

                final EditText idText= (EditText) findViewById(R.id.loginIdText);
                final EditText passwordText= (EditText) findViewById(R.id.loginPasswordText);

                final String loginId =idText.getText().toString();
                String loginPassword=passwordText.getText().toString();

                if (TextUtils.isEmpty(loginId)) {
                    idText.setError("ID를 입력하세요.");
                }

                if (TextUtils.isEmpty(loginPassword)) {
                    passwordText.setError("Password를 입력하세요.");
                }

                if(!TextUtils.isEmpty(loginId) && !TextUtils.isEmpty(loginPassword))
                {
                    /**아이디와 패스워드가 공백이 아니면 login통신*/

                    LoginUser loginUser=new LoginUser(loginId,loginPassword);//로그인유저 객체생성
                    //서버로 올리기
                    Call<Void> call=userService.postLoginUser(loginUser);
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if(response.isSuccessful()){
                                if(response.code()==200){ /**로그인이 성공하면*/
                                    //메인액티비티로 아이디 넘기고 화면전환
                                    Intent toMainIntent=new Intent(LoginActivity.this,MainActivity.class);
                                    toMainIntent.putExtra("userId",loginId);
                                    LoginActivity.this.startActivity(toMainIntent); }
                            }
                            else{   /**로그인이 실패하면*/
                                AlertDialog.Builder builder=new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("로그인에 실패하였습니다.")
                                        .setNegativeButton("다시 시도",null)
                                        .create()
                                        .show();
                                //텍스트 초기화
                                idText.setText("");
                                passwordText.setText("");
                            }

                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            /**요청실패*/
                            Log.i("Login액티비티", "통신에러");
                        }
                    });

                }


            }
        });



    }

}
