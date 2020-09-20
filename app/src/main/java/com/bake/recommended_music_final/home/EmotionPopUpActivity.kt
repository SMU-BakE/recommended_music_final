package com.bake.recommended_music_final.home

import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.bake.recommended_music_final.Navigator
import com.bake.recommended_music_final.R
import kotlinx.android.synthetic.main.activity_choice_emotion_popup.*
import org.jetbrains.anko.startActivity

class EmotionPopUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choice_emotion_popup)
        animateFork1() //fork 시계반대방향 회전 애니메이션
        animateFork2() //fork2 시계방향 회전 애니메이션

        var emotion: String = "happy"
        //감정선택결과 DB로 연결시키기
        imageButton_happy.setOnClickListener {
            Navigator(this).startHeartRatePopUpActivity("happy")
            finish()
        }
        imageButton_flutter.setOnClickListener {
            Navigator(this).startHeartRatePopUpActivity("flutter")
            finish()
        }
        imageButton_soso.setOnClickListener {
            Navigator(this).startHeartRatePopUpActivity("soso")
            finish()
        }
        imageButton_sad.setOnClickListener {
            Navigator(this).startHeartRatePopUpActivity("sad")
            finish()
        }
        imageButton_fun.setOnClickListener {
            Navigator(this).startHeartRatePopUpActivity("fun")
            finish()
        }
        imageButton_angry.setOnClickListener {
            Navigator(this).startHeartRatePopUpActivity("angry")
            finish()
        }

        //닫기 버튼 클릭
        button_close.setOnClickListener {
            finish()
        }

    }

    //fork 시계반대방향 회전 애니메이션
    private fun animateFork1() {
        val rotateFork = AnimationUtils.loadAnimation(this, R.anim.fork_animation)
        imageView_fork.animation = rotateFork
    }

    //fork2 시계방향 회전 애니메이션
    private fun animateFork2() {
        val rotateFork2 = AnimationUtils.loadAnimation(this, R.anim.fork2_animation)
        imageView_fork2.animation = rotateFork2
    }
}