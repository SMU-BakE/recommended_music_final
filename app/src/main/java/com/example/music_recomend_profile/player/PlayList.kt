package com.example.music_recomend_profile.player

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.music_recomend_profile.R
import com.example.music_recomend_profile.TimeUtils
import com.example.music_recomend_profile.database.DataExample
import kotlinx.android.synthetic.main.activity_play_list.*
import kotlin.properties.Delegates

class PlayList : Fragment() {

    private lateinit var listRV: RecyclerView
    private var position by Delegates.notNull<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_play_list, container, false)
        listRV = view.findViewById(R.id.playlistRV)


        activity!!.intent.extras?.getInt("position")?.let {
            position = it
        }

        dateTextView.text = DataExample().createRecordItem()[position].date?.let {
            TimeUtils().toDateString(
                it
            )
        }

        listRV.apply {
            adapter = DataExample().createRecordItem()[position].songList?.let {
                PlaylistAdapter(it,context)
            }
            layoutManager = LinearLayoutManager(context)
        }

        return view
    }

}