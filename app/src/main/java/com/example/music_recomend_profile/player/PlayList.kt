package com.example.music_recomend_profile.player

import android.content.Context
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.music_recomend_profile.R
import com.example.music_recomend_profile.TimeUtils
import com.example.music_recomend_profile.database.DataExample
import kotlinx.android.synthetic.main.activity_play_list.*
import kotlinx.android.synthetic.main.activity_player_home.*
import kotlinx.android.synthetic.main.unit_playlist.*
import kotlin.properties.Delegates

class PlayList : Fragment() {

    private lateinit var callback: OnBackPressedCallback
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

    /*//fragment 생명주기 이용하여 backPressed 구현
    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val fragmentManager: FragmentManager = activity!!.supportFragmentManager
                fragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.dropdown, R.anim.dropdown)
                    .hide(this@PlayList).commit()
                //queue 변수
                fragmentManager.popBackStack()

            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onDetach() {
        super.onDetach()
        callback.remove()
    }*/
}