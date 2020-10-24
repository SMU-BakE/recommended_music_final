package com.bake.recommended_music_final

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import com.bake.recommended_music_final.database.DataExample
import com.bake.recommended_music_final.firebase.Auth
import com.bake.recommended_music_final.firebase.Initialize
import com.bake.recommended_music_final.firebase.Profile

import kotlinx.android.synthetic.main.activity_initial.*
import org.jetbrains.anko.toast


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
        handler.postDelayed({ initialApp() }, 3000)
        animatePan()
    }

    private fun initialApp() {
        checkUser()
    }

    private fun checkUser() {
//       check login
        val user = Auth().user

        if (user == null) {
            Navigator(this).startLoginActivity()
            finish()
        } else {
            Navigator(this).startHomeActivity()
            DataExample.myCondtion.season = TimeUtils().getSeoson()
            DataExample.myCondtion.time = TimeUtils().getTime()
            finish()
        }

//        val profile = Profile().getProfile(user!!.uid).addOnCompleteListener{
//
//        }
//        if (profile == null) {
//            Navigator(this).startEditProfileActivity()
//        } else {
//            toast(profile.email.toString())
//        }
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