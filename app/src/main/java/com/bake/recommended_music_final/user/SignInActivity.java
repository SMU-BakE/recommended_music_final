package com.bake.recommended_music_final.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bake.recommended_music_final.R;
import com.bake.recommended_music_final.home.HomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {

    private MainBackPressCloseHandler mainBackPressCloseHandler;

    Button mLoginBtn, mResigeterBtn;
    EditText mEmailText, mPasswordText;
    TextView mPasswordFind;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mainBackPressCloseHandler = new MainBackPressCloseHandler(this);



        firebaseAuth =  FirebaseAuth.getInstance();
        //버튼 등록하기
        mResigeterBtn = findViewById(R.id.btn_register3);
        mLoginBtn = findViewById(R.id.btn_login3);
        mPasswordFind = findViewById(R.id.btn_findpw);
        mEmailText = findViewById(R.id.signup_email3);
        mPasswordText = findViewById(R.id.signup_pw3);


        //가입 버튼이 눌리면
        mResigeterBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //intent함수를 통해 register액티비티 함수를 호출한다.
                startActivity(new Intent(SignInActivity.this, SignupActivity.class));
                finish();

            }
        });

        //비밀번호 찾기 기능
        mPasswordFind.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                startActivity(new Intent(SignInActivity.this, FindAcitivty.class));
                finish();
            }
        });


        //로그인 버튼이 눌리면
        mLoginBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String email = mEmailText.getText().toString().trim();
                String pwd = mPasswordText.getText().toString().trim();

                if(email.length() == 0 || pwd.length() == 0){
                    Toast.makeText(SignInActivity.this, "아이디 또는 비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
                firebaseAuth.signInWithEmailAndPassword(email,pwd)
                        .addOnCompleteListener(SignInActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Intent intent = new Intent(SignInActivity.this, HomeActivity.class);
                                    startActivity(intent);
                                    finish();

                                }else{
                                    Toast.makeText(SignInActivity.this,"로그인 오류",Toast.LENGTH_SHORT).show();
                                    mEmailText.setText("");
                                    mPasswordText.setText("");
                                    mEmailText.requestFocus();
                                }
                            }
                        });

            }
        });
    }
    @Override
    public void onBackPressed(){
        mainBackPressCloseHandler.onBackPressed();
    }
}
