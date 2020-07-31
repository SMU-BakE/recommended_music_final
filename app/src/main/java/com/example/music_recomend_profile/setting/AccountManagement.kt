package com.example.music_recomend_profile.setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.music_recomend_profile.R
import com.example.music_recomend_profile.database.DataExample
import com.example.music_recomend_profile.database.UserProfile
import kotlinx.android.synthetic.main.activity_account_management.*
import kotlinx.android.synthetic.main.activity_account_management.editProfileImage
import kotlinx.android.synthetic.main.activity_account_management.editProfileSubtitle


class AccountManagement : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_management)

        setUserData()

        backSettingButton.setOnClickListener {
            onBackPressed()
        }


    }

    private fun setUserData() {

        //임시 데이터. DB 에서 불러와서 userProfile 에 넣어주세요.
        var userProfile : UserProfile = DataExample().createUser()

        editProfileName.text = userProfile.name
        editProfileEmailText.text = userProfile.email
        editProfileSubtitle.text = userProfile.introduce
        editProfileImage.setImageResource(userProfile.image!!)
        editProfileAge.text = userProfile.age.toString()
        editProfileSex.text = userProfile.sex
    }


}

