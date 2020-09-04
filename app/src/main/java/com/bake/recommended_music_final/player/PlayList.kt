package com.bake.recommended_music_final.player

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bake.recommended_music_final.R
import com.bake.recommended_music_final.TimeUtils
import com.bake.recommended_music_final.database.DataExample
import kotlin.properties.Delegates

class PlayList : Fragment() {

    private lateinit var listRV: RecyclerView
    private lateinit var dateTV: TextView
    private var position by Delegates.notNull<Int>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_play_list, container, false)
        listRV = view.findViewById(R.id.playlistRV)
        dateTV = view.findViewById(R.id.dateTextView)

        activity!!.intent.extras?.getInt("position")?.let {
            position = it
        }

        dateTV.text = DataExample().createRecordItem()[position].date?.let {
            TimeUtils().toDateString(
                it
            )
        }

        listRV.apply {
            adapter = DataExample().createRecordItem()[position].songList?.let {
                PlaylistAdapter(it, context, null)
            }
            layoutManager = LinearLayoutManager(context)
        }


        return view
    }

    fun setHighlight(songPosition: Int) {
//        updateView
        listRV.apply {
            adapter = DataExample().createRecordItem()[position].songList?.let {
                PlaylistAdapter(it, context, songPosition)
            }
            layoutManager = LinearLayoutManager(context)
        }
    }

}