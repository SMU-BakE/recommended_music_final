package com.bake.recommended_music_final

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import com.bake.recommended_music_final.database.DataExample
import com.bake.recommended_music_final.firebase.Initialize

import kotlinx.android.synthetic.main.activity_initial.*

//예시. 임시 변수
private var signIn: Boolean = true

class InitialActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_initial)


//        Initialize().callIncreaseCount("썸탈거야", "flutter", "good")
//        Initialize().callDecreaseCount("한숨", "funny", "bad")
//        Initialize().callRecommendMusic("flutter", "cloudy", 70)
    }

    override fun onStart() {
        super.onStart()

        startLoading()
    }

    private fun startLoading() {
        val handler = Handler()
        handler.postDelayed({ initialApp() }, 3000)
        animatePan()
    }

    private fun initialApp() {
        checkLogin()
        DataExample.myCondtion.season = TimeUtils().getSeoson()
        DataExample.myCondtion.time = TimeUtils().getTime()
//        DataExample.myCondtion.weather = TimeUtils().get
        finish()
    }

    private fun checkLogin() {
        //로그인 되어있으면 홈으로
        if (signIn) {
            Navigator(this).startHomeActivity()
        } else {
            //로그인 되어있지 않으면 로그인창으로
            Navigator(this).startLoginActivity()
        }
    }

    private fun animatePan() {
        val translatePan = AnimationUtils.loadAnimation(this, R.anim.pan_transtate)
        iv_pan.animation = translatePan

        val translateSalt = AnimationUtils.loadAnimation(this, R.anim.salt_transtate)
        iv_salt.animation = translateSalt

        val translateEmotion2 = AnimationUtils.loadAnimation(this, R.anim.emotion_transtate_2)
        iv_note1.animation = translateEmotion2
        iv_note2.animation = translateEmotion2
        iv_note3.animation = translateEmotion2

        val translateEmotion1 = AnimationUtils.loadAnimation(this, R.anim.emotion_transtate_1)
        iv_emotion.animation = translateEmotion1
    }

}