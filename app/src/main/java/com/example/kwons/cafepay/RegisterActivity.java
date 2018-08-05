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
    private String userId;
    private String userPassword;
    private String userName;
    private ArrayAdapter adapter;
    private Spinner spinner;
    private String userGender;
    private String userBirthYear;
    private String userBirthMonth;
    private String userBirthDate;
    private String userBirth;
    private boolean validate=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText registerIdText=findViewById(R.id.registerIdText);
        final Button registerIdCheckButton=findViewById(R.id.registerIdCheckButton);
        final EditText registerPasswordText=findViewById(R.id.registerPasswordText);
        final TextView registerErrorRecheckPassword=findViewById(R.id.registerErrorRecheckPassword);
        final EditText registerRecheckPasswordText=findViewById(R.id.registerRecheckPasswordText);
        final EditText registerUserNameText=findViewById(R.id.registerUserNameText);

        RadioGroup registerGenderGroup=findViewById(R.id.registerGenderGroup);
        int registerGenderGroupID=registerGenderGroup.getCheckedRadioButtonId();
        userGender=((RadioButton)findViewById(registerGenderGroupID)).getText().toString();
        registerGenderGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton genderButton=findViewById(i);
                userGender=genderButton.getText().toString();
            }
        });

        final EditText registerBirthYear=(EditText)findViewById(R.id.registerBirthYear);
        final EditText registerBirthDate=(EditText)findViewById(R.id.registerBirthDate);

        spinner=(Spinner)findViewById(R.id.registerBirthMonthSpinner);
        adapter=ArrayAdapter.createFromResource(this,R.array.month,android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);

        /**아이디 중복체크*/

        registerIdCheckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**아이디중복체크를 이미 했다면*/
                if(validate){
                    return;
                }
                /**아이디가 공백이면*/
                if (TextUtils.isEmpty(registerIdText.getText().toString())) {
                    AlertDialog.Builder builder=new AlertDialog.Builder(RegisterActivity.this);
                    builder.setMessage("ID는 공백일 수 없습니다.")
                            .setPositiveButton("확인",null)
                            .create()
                            .show();
                    return;
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
                                        builder.setMessage("중복된 ID입니다. 다시 입력해주세요.")
                                                .setNegativeButton("다시 시도",null)
                                                .create()
                                                .show();
                                        //텍스트초기화
                                        registerIdText.setText("");
                                        return;
                                    }
                                }
                                //중복ID가 없으면
                                AlertDialog.Builder builder=new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("사용할 수 있는 ID입니다.")
                                        .setPositiveButton("확인",null)
                                        .create()
                                        .show();
                                validate=true;
                                //registerIdCheckButton.setBackgroundColor(Color.rgb(92,209,229));
                            }
                            else{
                                int statusCode=response.code();
                                Log.i("Register액티비티","응답코드:"+statusCode);}
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
                    registerErrorRecheckPassword.setText("비밀번호가 일치하지 않습니다.");
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

        /**회원가입 버튼 클릭했을때*/
        Button registerButton=(Button)findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View view) {
                userId=registerIdText.getText().toString();
                userPassword=registerPasswordText.getText().toString();
                userName=registerUserNameText.getText().toString();
                userBirthYear=registerBirthYear.getText().toString();
                userBirthMonth=spinner.getSelectedItem().toString();
                userBirthDate=registerBirthDate.getText().toString();
                userBirth=userBirthYear+"/"+userBirthMonth+"/"+userBirthDate;

                //아이디 중복체크를 안했다면
                if(!validate){
                    AlertDialog.Builder builder=new AlertDialog.Builder(RegisterActivity.this);
                    builder.setMessage("중복체크를 해주세요.")
                            .setPositiveButton("확인",null)
                            .create()
                            .show();
                    return;
                }
                //빈칸이 있다면
                if(userId.equals("")||userPassword.equals("")||userName.equals("")||userBirthYear.equals("")||userBirthMonth.equals("")||userBirthDate.equals(""))
                {
                    AlertDialog.Builder builder=new AlertDialog.Builder(RegisterActivity.this);
                    builder.setMessage("빈 칸 없이 입력해주세요.")
                            .setNegativeButton("확인",null)
                            .create()
                            .show();
                    return;
                }

                /**드디어 서버에 회원가입정보 올리기!*/
                userService=RetrofitClient.getClient().create(UserService.class);
                //객체생성
                SignUpUser signUpUser=new SignUpUser(userBirth,userGender,userId,userName,userPassword);

                Call<Void> call=userService.postSignUpUser(signUpUser);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.isSuccessful()){
                            if(response.code()==200){ /**회원가입에 성공하면*/

                                AlertDialog.Builder builder=new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("회원가입 되었습니다.")
                                        .setPositiveButton("확인",null)
                                        .create()
                                        .show();
                                //로그인액티비티로 화면전환
                                Intent registerIntent=new Intent(RegisterActivity.this,LoginActivity.class);
                                RegisterActivity.this.startActivity(registerIntent);
                            }
                        }
                        else{   /**회원가입에 실패하면*/
                            AlertDialog.Builder builder=new AlertDialog.Builder(RegisterActivity.this);
                            builder.setMessage("회원가입에 실패했습니다.")
                                    .setNegativeButton("다시 시도",null)
                                    .create()
                                    .show();
                            int statusCode=response.code();
                            Log.i("Register액티비티","응답코드:"+statusCode);
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        /**요청실패*/
                        Log.i("Register액티비티", "통신에러");
                    }
                });

            }
        });
    }
}
