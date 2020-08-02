package com.example.music_recomend_profile.setting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.music_recomend_profile.R
import com.example.music_recomend_profile.TimeUtils
import com.example.music_recomend_profile.database.DataExample
import com.example.music_recomend_profile.database.UserProfile
import kotlinx.android.synthetic.main.activity_account_management.*
import kotlinx.android.synthetic.main.activity_account_management.profileImage
import kotlinx.android.synthetic.main.activity_account_management.profileSubtitle


class AccountManagement : AppCompatActivity() {

    private lateinit var userProfile: UserProfile
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_management)


        backSettingButton.setOnClickListener {
            onBackPressed()
        }

        editProfileButton.setOnClickListener {
            val intent = Intent(
                this,
                EditProfile::class.java
            )
            this.startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        setUserData()

    }

    private fun setUserData() {

        //임시 데이터. DB 에서 불러와서 userProfile 에 넣어주세요.
        userProfile = DataExample().getUser()

        profileName.text = userProfile.name
        profileEmailText.text = userProfile.email
        profileSubtitle.text = userProfile.introduce
        profileBirthday.text = TimeUtils().toDateString(userProfile.birthday!!)
        if (userProfile.sex == "선택 안함") {
            profileSex.text = "성별을 선택하세요."
        }else {
            profileSex.text = userProfile.sex
        }
        if (userProfile.imageSrc != null) {
            val requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
            Glide.with(this).load(userProfile.imageSrc).apply(requestOptions)
                .into(findViewById<ImageView>(R.id.profileImage))   //이미지를 로딩하고 into()메서드로 imageView 에 표시
        }
    }


}

