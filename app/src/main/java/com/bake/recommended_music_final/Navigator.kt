package com.bake.recommended_music_final

import android.content.Context
import android.content.Intent
import com.bake.recommended_music_final.user.SignInActivity

class Navigator(val context: Context) {
    fun goLoginPage() {
        val loginIntent = Intent(context, SignInActivity::class.java)
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        context.startActivity(loginIntent)
    }
}