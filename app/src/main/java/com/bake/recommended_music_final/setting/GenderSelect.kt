package com.bake.recommended_music_final.setting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.TextView
import com.bake.recommended_music_final.R
import org.jetbrains.anko.toast

class GenderSelect : AppCompatActivity() {

    private var sex: String? = null

    private lateinit var completeButton: TextView

    private lateinit var radioFemale: RadioButton
    private lateinit var radioMale: RadioButton
    private lateinit var radioEtc: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gender_select)

        intent.extras?.getString("sex")?.let {
            if (it.isNotEmpty()) {
                sex = it
            }
        }

        initialView()
    }

    private fun initialView() {
        completeButton = findViewById(R.id.completeButton)

        completeButton.setOnClickListener {
            val intent = Intent(this, EditProfile::class.java).putExtra("sex", sex)
//            this.startActivity(intent)
            setResult(1001, intent)
            finish()
        }

        radioFemale = findViewById(R.id.radio_female)
        radioMale = findViewById(R.id.radio_male)
        radioEtc = findViewById(R.id.radio_etc)

        when (sex) {
            "여성" -> radioFemale.isChecked = true
            "남성" -> radioMale.isChecked = true
            "선택 안함" -> radioEtc.isChecked = true
        }


    }

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked
            // Check which radio button was clicked
            when (view.getId()) {
                R.id.radio_male ->
                    if (checked) {
                        sex = "남성"
                        toast(sex.toString())

                    }
                R.id.radio_female ->
                    if (checked) {
                        sex = "여성"
                        toast(sex.toString())
                    }
                R.id.radio_etc ->
                    if (checked) {
                        sex = "선택 안함"
                        toast(sex.toString())
                    }
            }
        }
    }
}