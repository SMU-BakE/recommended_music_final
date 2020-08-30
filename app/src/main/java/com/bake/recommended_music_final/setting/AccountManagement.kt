package com.bake.recommended_music_final.setting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bake.recommended_music_final.R
import com.bake.recommended_music_final.TimeUtils
import com.bake.recommended_music_final.database.DataExample
import com.bake.recommended_music_final.database.UserProfile
import kotlinx.android.synthetic.main.activity_account_management.*
import kotlinx.android.synthetic.main.activity_account_management.profileSubtitle
import org.jetbrains.anko.toast


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

        changePassword.setOnClickListener {
            //비밀번호 변경 기능 연결
            toast("비밀번호 변경 기능")
        }

        membershipWithdrawal.setOnClickListener{
            //회원 탈퇴 기능 연결
            toast("회원탈퇴")
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
        } else {
            profileSex.text = userProfile.sex
        }

        if (userProfile.imageSrc != null) {
            val requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
            Glide.with(this).load(userProfile.imageSrc).apply(requestOptions)
                .into(findViewById<ImageView>(R.id.profileImage))
        }
    }


}

