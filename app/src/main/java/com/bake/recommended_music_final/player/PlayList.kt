package com.bake.recommended_music_final.player

import android.os.Bundle
import android.provider.ContactsContract
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
    private lateinit var date: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        date = arguments?.getString("date").toString()
        if (DataExample.songs == null) {
            activity!!.onBackPressed()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_play_list, container, false)
        listRV = view.findViewById(R.id.playlistRV)
        dateTV = view.findViewById(R.id.dateTextView)

        dateTV.text = date

        listRV.apply {
            adapter = PlaylistAdapter(DataExample.songs!!, context, null)
            layoutManager = LinearLayoutManager(context)
        }

        return view
    }

    fun setHighlight(songPosition: Int) {
//        updateView
        listRV.apply {
            adapter = PlaylistAdapter(DataExample.songs!!, context, songPosition)
            layoutManager = LinearLayoutManager(context)
        }
    }

}