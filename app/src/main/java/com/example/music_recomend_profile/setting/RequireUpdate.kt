package com.example.music_recomend_profile.setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.music_recomend_profile.R
import kotlinx.android.synthetic.main.activity_require_update.*

class RequireUpdate : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_require_update)

        backSettingButton.setOnClickListener {
            onBackPressed()
        }
    }
}