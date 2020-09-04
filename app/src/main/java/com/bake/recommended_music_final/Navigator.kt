package com.bake.recommended_music_final

import android.content.Context
import android.content.Intent
import com.bake.recommended_music_final.user.SignInActivity

class Navigator(val context: Context) {
    fun executeSignOut() {
        // 유저 데이터 삭제
        val intent = Intent(context, SignInActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }
}