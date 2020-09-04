package com.bake.recommended_music_final.user;

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bake.recommended_music_final.R

class FindPassword : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pwfind)

        //이동된 화면에서 이메일를 입력하고 '찾기' 버튼을 누르면
        //이메일이 유효한지 확인 후, 메일로 인증번호를 보내고
        //앱에서 인증하면 비밀번호를 알려주는 방식으로 진행
    }
}