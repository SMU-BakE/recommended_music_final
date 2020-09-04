package com.bake.recommended_music_final.userfeed

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bake.recommended_music_final.R
import com.bake.recommended_music_final.database.DataExample
import com.bake.recommended_music_final.database.UserProfile
import com.bake.recommended_music_final.friends.FindFriend
import com.bake.recommended_music_final.friends.Follower
import com.bake.recommended_music_final.friends.Following
import com.bake.recommended_music_final.setting.SettingListActivity
import kotlinx.android.synthetic.main.activity_user_feed.*


class UserFeedActivity : AppCompatActivity() {
    private lateinit var recordListFragment: RecordListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_feed)

        setRecordList()


        backHomeButton.setOnClickListener {
            onBackPressed()
        }

        profileSettingButton.setOnClickListener {
            val intent = Intent(
                this,
                SettingListActivity::class.java
            )
            startActivity(intent)
        }

        profileFollower.setOnClickListener {
            val intent = Intent(
                this,
                Follower::class.java
            )
            startActivity(intent)
        }

        followerNum.setOnClickListener {
            val intent = Intent(
                this,
                Follower::class.java
            )
            startActivity(intent)
        }

        profileFollowing.setOnClickListener {
            val intent = Intent(
                this,
                Following::class.java
            )
            startActivity(intent)
        }

        followingNum.setOnClickListener {
            val intent = Intent(
                this,
                Following::class.java
            )
            startActivity(intent)
        }

        findFriends.setOnClickListener {
            val intent = Intent(
                this,
                FindFriend::class.java
            )
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        setUserData()
    }


    private fun setRecordList() {
        recordListFragment =
            RecordListFragment()
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(
            R.id.recordFrame,
            RecordListFragment()
        ).commit()
    }

    private fun setUserData() {

        var userList: UserProfile = DataExample().getUser()

        profileName.text = userList.name
        followerNum.text = userList.follower.toString()
        followingNum.text = userList.following.toString()
        profileSubtitle.text = userList.introduce
        if (userList.imageSrc != null) {
            val requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
            Glide.with(this).load(userList.imageSrc).apply(requestOptions)
                .into(findViewById<ImageView>(R.id.profileImage))
        }
    }


}

