package com.bake.recommended_music_final

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.bake.recommended_music_final.home.HomeActivity
import com.bake.recommended_music_final.user.SignInActivity

//예시. 임시 변수
private var signIn: Boolean = false

class InitialActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_initial)
    }

    override fun onStart() {
        super.onStart()

       startLoading()
    }

    private fun startLoading() {
        val handler = Handler()
        handler.postDelayed({ initialApp() }, 1000)
    }

    private fun initialApp() {
        checkLogin()
        finish()
    }

    private fun checkLogin() {
        //로그인 되어있으면 홈으로

        if (signIn) {
            var intent = Intent(
                this,
                HomeActivity::class.java
            )
            startActivity(intent)
        } else {
            //로그인 되어있지 않으면 로그인창으로
            intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    }

}