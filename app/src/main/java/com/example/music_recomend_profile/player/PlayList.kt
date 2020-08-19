package com.example.music_recomend_profile.player

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.music_recomend_profile.R
import com.example.music_recomend_profile.database.DataExample
import kotlin.properties.Delegates

class PlayList : AppCompatActivity() {

    private lateinit var listRV: RecyclerView
    private var position by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_list)
        initialView()
    }

    private fun initialView() {
        listRV = findViewById(R.id.playlistRV)

        intent.extras?.getInt("position")?.let {
                position  = it
        }

        listRV.apply {
            adapter = DataExample().createRecordItem()[position].songList?.let {
                PlaylistAdapter(

                    //예시 데이터. 바꿔주세요~
                    songList = it,
                    context = this@PlayList
                )
            }
            layoutManager = LinearLayoutManager(this@PlayList)
        }
    }

    fun playButtonClick1(v: View) {


    }
}