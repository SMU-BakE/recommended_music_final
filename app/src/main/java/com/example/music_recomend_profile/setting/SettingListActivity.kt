package com.example.music_recomend_profile.setting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.music_recomend_profile.R
import com.example.music_recomend_profile.database.RecordItem
import com.example.music_recomend_profile.database.SettingMenu
import kotlinx.android.synthetic.main.activity_setting_list.*
import kotlinx.android.synthetic.main.activity_user_feed.*

class SettingListActivity : AppCompatActivity() {
    private lateinit var settingRV: RecyclerView
    private val settingMenu = arrayListOf<SettingMenu>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting_list)

        backStudioButton.setOnClickListener{
            onBackPressed()
        }


        settingRV = findViewById(R.id.settingRV)

        settingRV.apply {
            adapter = SettingListAdapter(
                settingMenu = setSettingMenuData(),
                context = this@SettingListActivity
            )
            layoutManager = LinearLayoutManager(this@SettingListActivity)
        }

    }

    private fun setSettingMenuData(): ArrayList<SettingMenu> {

        val arrayList: ArrayList<SettingMenu> = ArrayList()

        arrayList.add(
            SettingMenu(
                R.drawable.icon_menu,
                R.drawable.text_setting
            )
        )
        arrayList.add(
            SettingMenu(
                R.drawable.icon_menu,
                R.drawable.text_update
            )
        )
        arrayList.add(
            SettingMenu(
                R.drawable.icon_menu,
                R.drawable.text_logout
            )
        )



        return arrayList
    }


}