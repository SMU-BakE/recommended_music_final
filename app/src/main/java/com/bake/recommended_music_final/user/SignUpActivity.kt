package com.bake.recommended_music_final.user;

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bake.recommended_music_final.R
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        //'중복확인' 버튼을 눌렀을 시 signup_id에 입력된 아이디와 데이터베이스의 아이디 열을 비교하여 없으면 "사용가능한 아이디입니다."라는 메세지 출력
        signup_idck.setOnClickListener {
            //만약 signup_id의 길이가 0이거나 DB의 아이디들 중 같은 것이 있다면,,
            if (signup_email == null/*데이터베이스의 아이디 부분*/) {
                Toast.makeText(this@SignUpActivity, "중복된 아이디입니다.", Toast.LENGTH_LONG).show()
            }
            else if(signup_email.length() == 0){
                Toast.makeText(this@SignUpActivity, "아이디를 적어주세요.", Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this@SignUpActivity, "사용가능한 아이디입니다.", Toast.LENGTH_LONG).show()
                val Intent_auth = Intent(this, EmailAuth::class.java)
                startActivity(Intent_auth)//이메일 인증 액티비티로 넘어감
            }
        }

        //비밀번호는 6-12로 지정하고, 특수문자/영어/숫자 를 포함해서 작성하도록 한다.
        //조건은 일단 만든 이후 추가적으로 작업한다.
        //'가입하기' 버튼을 눌렀을 시 조건에 합당하면 메인으로 화면이 이동함
        signup_button.setOnClickListener {

            //비밀번호와 비밀번호확인 란의 값이 다르다면
            //"비밀번호가 일치하지 않음"을 토스트 메세지로 출력한다
            if (!signup_pw.getText().toString().equals(signup_pwck.getText().toString())) {
                    Toast.makeText(this@SignUpActivity, "비밀번호가 일치하지 않습니다. 다시 입력해 주세요.", Toast.LENGTH_SHORT).show()
                    signup_pw.setText("")
                    signup_pwck.setText("")
                    signup_pw.requestFocus()
            }
            else{
                Toast.makeText(this@SignUpActivity, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, SignInActivity::class.java)
                startActivity(intent)
                finish()
            }

        }
    }
}
