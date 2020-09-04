package com.bake.recommended_music_final.user;

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bake.recommended_music_final.InitialActivity
import com.bake.recommended_music_final.R
import com.bake.recommended_music_final.home.HomeActivity
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        //btn_login.setEnabled(false); // 로그인 버튼 비활성화

        //'로그인' 버튼 눌렀을 시 Login.kt로 넘어감
        //1. DB와 비교 후 있는 ID, PW이면 로그인 버튼 누를 시 Login.kt로 넘어감
        //2. 없는 정보일 경우, "로그인 오류입니다." 라는 메세지를 띄운 뒤 다시 MainActivity.kt로 이동
        btn_login.setOnClickListener {
            //입력 ID, PW와 DB를 비교하는 부분


            //DB에 있는 정보로 로그인 할 경우, 간단한 Toast 메세지를 띄운 뒤 Login.kt로 이동함
            //Login.kt는 추후 세연 '홈 화면'으로 연결될 예정
            if(main_id.length() != 0 && main_pw.length() != 0/*데이터베이스에 해당 아이디와 비밀번호가 존재할 시*/) {
                //Toast.makeText(this, "로그인 성공입니다.", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }

            //DB에 없는 정보로 로그인 할 경우, 토스트 메세지 출력 후 다시 MainActivity.kt로 이동
            else {/*데이터베이스에 없는 정보로 로그인 할 경우, 오류 메세지를 출력*/
                Toast.makeText(this, "로그인 오류입니다.", Toast.LENGTH_LONG).show()
            }
        }

        //'비밀번호 찾기' 버튼을 눌렀을 시 Pwfind.kt 으로 넘어감
        btn_findpw.setOnClickListener {
            val intent = Intent(this, FindPassword::class.java)
            startActivity(intent)
        }

        //'회원가입' 버튼 눌렀을 시 Signup.kt로 넘어감
        btn_signup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}
