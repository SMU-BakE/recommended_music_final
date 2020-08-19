package com.example.music_recomend_profile

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.music_recomend_profile.database.RecordItem
import com.example.music_recomend_profile.player.PlayList
import com.example.music_recomend_profile.player.PlayerHome

class Adapter(var context: Context, var recordItemList: ArrayList<RecordItem>) :
    RecyclerView.Adapter<Adapter.ItemHolder>() {


    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var titleTV: TextView = itemView.findViewById(R.id.recordEmotion)
        var dateTV: TextView = itemView.findViewById(R.id.recordDate)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val viewHolder = LayoutInflater.from(parent.context)
            .inflate(R.layout.unit_record, parent, false)
        return ItemHolder(viewHolder)
    }

    override fun getItemCount(): Int {
        return recordItemList.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val recordItem: RecordItem = recordItemList[position]
        holder.dateTV.text = recordItem.date?.let { TimeUtils().toDateString(it) }
        holder.titleTV.text = recordItem.emotion

        holder.titleTV.setOnClickListener {
            //Toast.makeText(context, recordItem.emotion, Toast.LENGTH_SHORT).show()
            val intent = Intent(
                context,
                PlayerHome::class.java
            ).putExtra("position",position)
            context.startActivity(intent)

        }
    }

}