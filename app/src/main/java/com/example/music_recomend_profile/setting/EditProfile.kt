package com.example.music_recomend_profile.setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.music_recomend_profile.R
import com.example.music_recomend_profile.TimeUtils
import com.example.music_recomend_profile.database.DataExample
import kotlinx.android.synthetic.main.activity_account_management.*
import kotlinx.android.synthetic.main.activity_edit_profile.*
import org.jetbrains.anko.toast

class EditProfile : AppCompatActivity() {

    //임시 데이터. DB 에서 불러와서 userProfile 에 넣어주세요.
    val userProfile = DataExample().getUser()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        updateView()

        //캘린더 업데이트
        editProfileBirthInput.setOnClickListener {
            val newFragment = DateDialogFragment { year: Int, month: Int, day: Int ->
                val birthday: TextView = findViewById(R.id.editProfileBirthInput)
                birthday.text = TimeUtils().dateToString(year, month, day)
                val datetime = TimeUtils.DateTime(year, month, day, 0, 0)

                userProfile.birthday = TimeUtils().getTimestamp(datetime)
            }
            newFragment.show(supportFragmentManager, "datePicker")
        }

        completeButton.setOnClickListener {
            setProfile()
        }

        cancelButton.setOnClickListener {
            finish()
        }
    }

    private fun updateView() {


        editProfileNameInput.setText(userProfile.name)
        editProfileSubtitleInput.setText(userProfile.introduce)
        editProfileEmailText.text = userProfile.email

        val birthdayInput: TextView = findViewById(R.id.editProfileBirthInput)
        userProfile.birthday?.let {
            birthdayInput.text = TimeUtils().toDateString(userProfile.birthday!!)
        }

        val profileImage: ImageView = findViewById(R.id.editProfileImage)
        userProfile.image?.let {
            profileImage.setImageResource(userProfile.image!!)
        }

    }

    private fun setProfile() {
        userProfile.name = editProfileNameInput.text.toString()
        userProfile.introduce = editProfileSubtitleInput.text.toString()


        DataExample().updateMyProfile(userProfile)
        toast("변경 완료")
        finish()

    }
}