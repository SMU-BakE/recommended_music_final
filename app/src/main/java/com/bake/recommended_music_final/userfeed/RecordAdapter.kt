package com.bake.recommended_music_final.userfeed

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bake.recommended_music_final.Navigator
import com.bake.recommended_music_final.R
import com.bake.recommended_music_final.TimeUtils
import com.bake.recommended_music_final.database.DataExample
import com.bake.recommended_music_final.database.RecordItem
import com.bake.recommended_music_final.player.PlayerHome

class Adapter(var context: Context, private var recordItemList: ArrayList<RecordItem>) :
    RecyclerView.Adapter<Adapter.ItemHolder>() {

    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var titleTV: TextView = itemView.findViewById(R.id.recordEmotion)
        var dateTV: TextView = itemView.findViewById(R.id.recordDate)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val viewHolder = LayoutInflater.from(parent.context)
            .inflate(R.layout.unit_record, parent, false)
        return ItemHolder(
            viewHolder
        )
    }

    override fun getItemCount(): Int {
        return recordItemList.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val recordItem: RecordItem = recordItemList[position]
        val emotion = recordItem.emotion
        val date = recordItem.date?.let { TimeUtils().toDateString(it) }
        holder.dateTV.text = date
        holder.titleTV.text = emotion
        holder.titleTV.setOnClickListener {
            DataExample.songs = recordItem.songList
            if (emotion == null || date == null) {
                throw Error("이모션이 없어라")
            }
            Navigator(context).startPlayerHomeActivity(position, emotion, date)
        }
    }

}