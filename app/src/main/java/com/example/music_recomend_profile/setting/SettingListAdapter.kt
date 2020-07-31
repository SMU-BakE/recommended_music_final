package com.example.music_recomend_profile.setting

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.music_recomend_profile.R
import com.example.music_recomend_profile.database.SettingMenu
import kotlinx.android.synthetic.main.unit_setting.view.*


class SettingListAdapter(
    private val settingMenu: List<SettingMenu>, private val context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.unit_setting, parent, false)
        )

    override fun getItemCount(): Int = settingMenu.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = settingMenu[position]
        holder.itemView.settingIcon.setImageResource(data.icon!!)
        holder.itemView.settingMenu.setImageResource(data.title!!)

        holder.itemView.settingMenu.setOnClickListener {
            when (position) {
                0 -> context.startActivity(Intent(context, AccountManagement::class.java))
                1 -> context.startActivity(Intent(context,RequireUpdate::class.java))


                //로그아웃 기능 연결해주세요
                2 -> Toast.makeText(context, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show()
            }
        }

    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}