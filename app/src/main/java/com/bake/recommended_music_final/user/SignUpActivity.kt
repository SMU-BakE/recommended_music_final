package com.bake.recommended_music_final.user;

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bake.recommended_music_final.R
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.jetbrains.anko.toast
import java.util.regex.Pattern

class SignUpActivity : AppCompatActivity() {

    private fun checkEmail(email: String): Boolean {
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches()
    }

    private fun checkPassword(password: String): Boolean
    {
        return PASSWORD_PATTERN.matcher(password).matches()
    }

    companion object {
        val EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
        )

        val PASSWORD_PATTERN = Pattern.compile("^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-zA-Z]).{8,20}$")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val password = signup_pw.text.toString()
        val regExp_password = "([0-9].*[!,@,#,^,&,*,(,)])|([!,@,#,^,&,*,(,)].*[0-9])"
        val passwordpattern_symbol = Pattern.compile(regExp_password)
        val passwordmatcher_symbol = passwordpattern_symbol.matcher(password)
        /*fun isValidEmail(id: String): Boolean {
            *//*val regEmail = Regex("/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}\$/i")
            if(!email.matches(regEmail))
                return true
            else
                return false*//*
            *//*return Patterns.EMAIL_ADDRESS.matcher(email).matches()*//*
        }*/



        /*val regPassword = Regex("^[A-Za-z0-9_@./#&+-]*.{6,20}\$") // ^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&]).{8,15}.$
        val regEmail= Regex("/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i") // 이메일 형식*/


        //'중복확인' 버튼을 눌렀을 시 signup_id에 입력된 아이디와 데이터베이스의 아이디 열을 비교하여 없으면 "사용가능한 아이디입니다."라는 메세지 출력
        signup_idck.setOnClickListener {

            val email = signup_email.text.toString()
            val regExp_email = "/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}\$/i"
            val emailpattern_symbol = Pattern.compile(regExp_email)
            val emailmatcher_symbol = emailpattern_symbol.matcher(email)

            //만약 signup_id의 길이가 0이거나 DB의 아이디들 중 같은 것이 있다면,,
            if (signup_email == null/*데이터베이스의 아이디 부분*/) {
                Toast.makeText(this@SignUpActivity, "아이디를 입력하지 않으셨거나, 중복된 아이디입니다.", Toast.LENGTH_LONG).show()
                signup_email.setText("")
                signup_email.requestFocus()
            }
            else if(signup_email.length() == 0){
                Toast.makeText(this@SignUpActivity, "아이디를 적어주세요.", Toast.LENGTH_LONG).show()
                signup_email.requestFocus()
            }
            else{
                if(!checkEmail(signup_email.getText().toString())) {
                    Toast.makeText(this@SignUpActivity, "이메일 형식이 잘못되었습니다.", Toast.LENGTH_SHORT).show()
                    signup_email.setText("")
                    signup_email.requestFocus()
                }

                else {
                    Toast.makeText(this@SignUpActivity, "사용가능한 아이디입니다.", Toast.LENGTH_LONG).show()
                    val intentAuth = Intent(this, EmailAuth::class.java)
                    intentAuth.putExtra("이메일 값", signup_email.text.toString())
                    startActivity(intentAuth)//이메일 인증 액티비티로 넘어감
                }
            }
        }

        //비밀번호는 6-12로 지정하고, 특수문자/영어/숫자 를 포함해서 작성하도록 한다.
        //조건은 일단 만든 이후 추가적으로 작업한다.
        //'가입하기' 버튼을 눌렀을 시 조건에 합당하면 메인으로 화면이 이동함
        //비밀번호 변수

        signup_button.setOnClickListener {

            //비밀번호 정규식 만들기
            if(!checkPassword(signup_pw.getText().toString())){
                Toast.makeText(this@SignUpActivity, "비밀번호를 다시 설정해주세요. Tip : 8-15글자의 영문, 숫자, 특수문자를 조합하여야 합니다.", Toast.LENGTH_LONG).show()
                signup_pw.setText("")
                signup_pwck.setText("")
                signup_pw.requestFocus()
            }
            else{
                //비밀번호와 비밀번호확인 란의 값이 다르다면
                //"비밀번호가 일치하지 않음"을 토스트 메세지로 출력한다
                if (!signup_pw.text.toString().equals(signup_pwck.text.toString())) {
                    Toast.makeText(this@SignUpActivity, "비밀번호가 일치하지 않습니다. 다시 입력해 주세요.", Toast.LENGTH_SHORT).show()
                    signup_pw.setText("")
                    signup_pwck.setText("")
                    signup_pw.requestFocus()
                }
                else{
                    Toast.makeText(this@SignUpActivity, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }
}
