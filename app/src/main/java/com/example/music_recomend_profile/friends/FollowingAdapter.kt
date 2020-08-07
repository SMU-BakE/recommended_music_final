package com.example.music_recomend_profile.friends

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.music_recomend_profile.R
import com.example.music_recomend_profile.database.UserProfile
import kotlinx.android.synthetic.main.unit_following.view.*

class FollowingAdapter(
    private val following: List<UserProfile>, private val context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var iconView: ImageView

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.unit_following, parent, false)
        )

    override fun getItemCount(): Int = following.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = following[position]

        holder.itemView.followingName.text = data.name
        holder.itemView.followingEmail.text=data.email

        //이미지 데베에서 받아서 띄워주세요.
        //iconView.setImageResource()




    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}