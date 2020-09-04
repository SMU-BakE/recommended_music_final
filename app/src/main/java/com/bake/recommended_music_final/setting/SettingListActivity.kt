package com.bake.recommended_music_final.setting


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bake.recommended_music_final.R
import com.bake.recommended_music_final.database.SettingMenu
import kotlinx.android.synthetic.main.activity_setting_list.*

class SettingListActivity : AppCompatActivity() {
    private lateinit var settingRV: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting_list)

        backStudioButton.setOnClickListener {
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

        val settingMenu: ArrayList<SettingMenu> = ArrayList()

        settingMenu.add(
            SettingMenu(
                R.drawable.icon_menu,
                R.drawable.text_setting
            )
        )
        settingMenu.add(
            SettingMenu(
                R.drawable.icon_menu,
                R.drawable.text_update
            )
        )
        settingMenu.add(
            SettingMenu(
                R.drawable.icon_menu,
                R.drawable.text_logout
            )
        )



        return settingMenu
    }


}