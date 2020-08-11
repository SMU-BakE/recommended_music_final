package com.example.music_recomend_profile.friends

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.music_recomend_profile.R
import com.example.music_recomend_profile.database.UserProfile
import kotlinx.android.synthetic.main.unit_find_firend.view.*

class FindFriendAdapter(
    private val findFriend: List<UserProfile>, private val context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var iconView: ImageView

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.unit_find_firend, parent, false)
        )

    override fun getItemCount(): Int = findFriend.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = findFriend[position]

        holder.itemView.friendName.text = data.name
        holder.itemView.friendEmail.text = data.email

        //이미지 데베에서 받아서 띄워주세요.
        //iconView.setImageResource()


    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}