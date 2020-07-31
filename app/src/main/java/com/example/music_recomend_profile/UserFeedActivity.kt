package com.example.music_recomend_profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.music_recomend_profile.database.DataExample
import com.example.music_recomend_profile.database.UserProfile
import com.example.music_recomend_profile.setting.SettingListActivity
import kotlinx.android.synthetic.main.activity_user_feed.*
import org.jetbrains.anko.toast


class UserFeedActivity : AppCompatActivity() {
    private lateinit var recordListFragment: RecordListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_feed)

        setFrag(0)


        backHomeButton.setOnClickListener{
            //home 으로 뒤로가기 만들어야함
            toast("뒤로가기 버튼")
        }

        profileSettingButton.setOnClickListener {
            val intent = Intent(this,
                SettingListActivity::class.java)
            this.startActivity(intent)
        }
    }

     override fun onStart() {
        super.onStart()
         setUserData()
    }


    private fun setFrag(FragNum: Int) {
        recordListFragment = RecordListFragment()
        val ft = supportFragmentManager.beginTransaction()
        when (FragNum) {
            0 -> ft.replace(R.id.recordFrame, RecordListFragment()).commit()
        }
    }

    private fun setUserData() {

        var userList : UserProfile = DataExample().getUser()

        profileName.text = userList.name
        followerNum.text = userList.follower.toString()
        followingNum.text = userList.following.toString()
        profileSubtitle.text = userList.introduce
        profileImage.setImageResource(userList.image!!)
    }


}

