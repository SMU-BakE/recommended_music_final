package com.bake.recommended_music_final.friends

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bake.recommended_music_final.R
import com.bake.recommended_music_final.database.UserProfile
import kotlinx.android.synthetic.main.unit_follower.view.*

class FollowerAdapter(
    private val follower: List<UserProfile>, private val context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var iconView: ImageView

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.unit_follower, parent, false)
        )

    override fun getItemCount(): Int = follower.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = follower[position]

        holder.itemView.followerName.text = data.name
        holder.itemView.followerEmail.text = data.email

        //이미지 데베에서 받아서 띄워주세요.
        //iconView.setImageResource()


    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}