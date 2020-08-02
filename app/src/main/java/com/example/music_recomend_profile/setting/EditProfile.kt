package com.example.music_recomend_profile.setting

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.music_recomend_profile.R
import com.example.music_recomend_profile.TimeUtils
import com.example.music_recomend_profile.database.DataExample
import kotlinx.android.synthetic.main.activity_account_management.*
import kotlinx.android.synthetic.main.activity_edit_profile.*
import org.jetbrains.anko.toast

class EditProfile : AppCompatActivity() {

    //임시 데이터. DB 에서 불러와서 userProfile 에 넣어주세요.
    val userProfile = DataExample().getUser()

    private val OPEN_GALLERY = 1;


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

        editProfileImageButton.setOnClickListener {
            openGallery()
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

        if (userProfile.imageSrc != null) {
            val requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
            Glide.with(this).load(userProfile.imageSrc).apply(requestOptions)
                .into(findViewById<ImageView>(R.id.editProfileImage))   //이미지를 로딩하고 into()메서드로 imageView 에 표시
        }


    }

    private fun setProfile() {
        userProfile.name = editProfileNameInput.text.toString()
        userProfile.introduce = editProfileSubtitleInput.text.toString()


        DataExample().updateMyProfile(userProfile)
        toast("변경 완료")
        finish()

    }


    //갤러리 열기 함수
    private fun openGallery() {
        val intent: Intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.setType("image/*")
        //결과를 수신하기 위하여 startActivity 대신 startActivityForResult 를 호출
        startActivityForResult(intent, OPEN_GALLERY)
    }

    //사용자가 startActivityForResult 의 활동을 하고오면  onActivityResult 를 호출
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {

            if (requestCode == OPEN_GALLERY) {
                //toast("open")
                var currentImageUrl: Uri? = data?.data
                userProfile.imageSrc = currentImageUrl.toString()
                //toast("Uri: ${currentImageUrl}")
                try {
                    //toast(imageSrc.toString())
                    val requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
                    Glide.with(this).load(userProfile.imageSrc).apply(requestOptions)
                        .into(findViewById<ImageView>(R.id.editProfileImage))   //이미지를 로딩하고 into()메서드로 imageView 에 표시

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } else {
                Log.d("ActivityResult", "something wrong")
            }
        }
    }
}