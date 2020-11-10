package com.bake.recommended_music_final.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bake.recommended_music_final.Navigator
import com.bake.recommended_music_final.R
import com.bake.recommended_music_final.TimeUtils
import com.bake.recommended_music_final.database.DataExample
import com.bake.recommended_music_final.database.Song
import kotlinx.android.synthetic.main.unit_song.view.*

class HomeListAdapter(
    private val songList: List<Song>, private val context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.unit_song, parent, false)
        )

    override fun getItemCount(): Int = songList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, songPosition: Int) {
        val data = songList[songPosition]

        holder.itemView.text1.text = data.songName
        holder.itemView.text2.text = data.singer
        holder.itemView.imageView_play.setOnClickListener {
            Navigator(context).startPlayerHomeActivity(songPosition,DataExample.myCondtion.emotion, TimeUtils().getCurrentDate())
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}