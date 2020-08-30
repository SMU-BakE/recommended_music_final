package com.bake.recommended_music_final.setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bake.recommended_music_final.R
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