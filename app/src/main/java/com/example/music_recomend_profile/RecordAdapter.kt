package com.example.music_recomend_profile

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.music_recomend_profile.database.RecordItem

class Adapter(var context: Context, var arrayList: ArrayList<RecordItem>) :
    RecyclerView.Adapter<Adapter.ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val viewHolder = LayoutInflater.from(parent.context)
            .inflate(R.layout.unit_record, parent, false)
        return ItemHolder(viewHolder)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val recordItem: RecordItem = arrayList[position]

        holder.dateTV.text = recordItem.date.toString()
        holder.titleTV.text = recordItem.title

        holder.titleTV.setOnClickListener {
            Toast.makeText(context, recordItem.title, Toast.LENGTH_LONG).show()
        }

    }

    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var titleTV: TextView = itemView.findViewById(R.id.recordEmotion)
        var dateTV: TextView = itemView.findViewById(R.id.recordDate)
    }
}