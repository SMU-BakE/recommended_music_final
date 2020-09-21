package com.bake.recommended_music_final.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.bake.recommended_music_final.Navigator
import com.bake.recommended_music_final.R
import com.bake.recommended_music_final.TimeUtils
import com.bake.recommended_music_final.database.Condition
import com.bake.recommended_music_final.database.DataExample
import com.bake.recommended_music_final.database.DataExample.Companion.songs
import com.bake.recommended_music_final.database.Song
import com.bake.recommended_music_final.firebase.Initialize
import com.google.android.gms.tasks.OnCompleteListener
import kotlinx.android.synthetic.main.activity_choice_emotion_popup.*

class EmotionPopUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choice_emotion_popup)
        animateFork1() //fork 시계반대방향 회전 애니메이션
        animateFork2() //fork2 시계방향 회전 애니메이션

        var emotion: String = "happy"
        //감정선택결과 DB로 연결시키기
        imageButton_happy.setOnClickListener {
            submitEmotion("happy")
        }
        imageButton_flutter.setOnClickListener {
            submitEmotion("flutter")
        }
        imageButton_soso.setOnClickListener {
            submitEmotion("soso")
        }
        imageButton_sad.setOnClickListener {
            submitEmotion("sad")
        }
        imageButton_fun.setOnClickListener {
            submitEmotion("funny")
        }
        imageButton_angry.setOnClickListener {
            submitEmotion("angry")
        }

        //닫기 버튼 클릭
        button_close.setOnClickListener {
            finish()
        }
    }

    private fun submitEmotion(emotion: String) {
        DataExample.myCondtion.emotion = emotion
        Initialize().callRecommendMusic(
            DataExample.myCondtion
        ).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val songList = task.result
                songs = songList
                Log.d("songs updated", songs.toString())
                setResult(1001, Intent().putExtra("result", true))
                Navigator(this).startHeartRatePopUpActivity(emotion)
                finish()
            }
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