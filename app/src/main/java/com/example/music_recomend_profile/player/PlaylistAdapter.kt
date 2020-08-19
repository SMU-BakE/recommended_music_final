package com.example.music_recomend_profile.player

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.music_recomend_profile.R
import com.example.music_recomend_profile.database.Song
import kotlinx.android.synthetic.main.unit_playlist.view.*

class PlaylistAdapter(
    private val songList: List<Song>, private val context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.unit_playlist, parent, false)
        )

    override fun getItemCount(): Int = songList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = songList[position]

        holder.itemView.songName.text = data.songName
        holder.itemView.singer.text = data.singer



    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}