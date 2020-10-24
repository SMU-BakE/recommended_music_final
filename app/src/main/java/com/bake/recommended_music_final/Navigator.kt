package com.bake.recommended_music_final

import android.content.Context
import android.content.Intent
import com.bake.recommended_music_final.firebase.Auth
import com.bake.recommended_music_final.friends.FindFriend
import com.bake.recommended_music_final.friends.Follower
import com.bake.recommended_music_final.friends.Following
import com.bake.recommended_music_final.home.HeartRatePopUpActivity
import com.bake.recommended_music_final.home.HomeActivity
import com.bake.recommended_music_final.player.PlayerHome
import com.bake.recommended_music_final.setting.EditProfile
import com.bake.recommended_music_final.setting.SettingListActivity
import com.bake.recommended_music_final.user.SignInActivity

class Navigator(val context: Context) {
    fun executeSignOut() {
        Auth().doSignOut()
        val intent = Intent(context, SignInActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

    fun startHomeActivity() {
        val intent = Intent(context, HomeActivity::class.java)
        context.startActivity(intent)
    }

    fun startLoginActivity() {
        val intent = Intent(context, SignInActivity::class.java)
        context.startActivity(intent)
    }

    fun startPlayerHomeActivity(position: Int) {
        val intent = Intent(context, PlayerHome::class.java).putExtra("position", position)
        context.startActivity(intent)
    }

    fun startSettingListActivity() {
        val intent = Intent(context, SettingListActivity::class.java)
        context.startActivity(intent)
    }

    fun startFollowerActivity() {
        val intent = Intent(context, Follower::class.java)
        context.startActivity(intent)
    }

    fun startFollowingActivity() {
        val intent = Intent(context, Following::class.java)
        context.startActivity(intent)
    }

    fun startFindFriendActivity() {
        val intent = Intent(context, FindFriend::class.java)
        context.startActivity(intent)
    }

    fun startHeartRatePopUpActivity(emotion: String) {
        val intent =
            Intent(context, HeartRatePopUpActivity::class.java).putExtra("emotion", emotion)
        context.startActivity(intent)
    }

    fun startEditProfileActivity() {
        val intent = Intent(context, EditProfile::class.java)
        context.startActivity(intent)
    }
}