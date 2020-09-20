package com.bake.recommended_music_final.player

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import com.bake.recommended_music_final.R
import com.bake.recommended_music_final.database.Song
import kotlin.properties.Delegates


class ViewMorePopup(val context: Context, song: Song) {
    private val dialog = Dialog(context)   //부모 액티비티의 context 가 들어감

    private lateinit var musicVideoButton: TextView
    private lateinit var favoriteButton: ImageView
    private lateinit var shareButton: ImageView
    private lateinit var songTitleName: TextView
    private lateinit var singerName: TextView

    private var favoriteTemp by Delegates.notNull<Boolean>()

    private val songName = if (song.songName != null) {
        song.songName!!
    } else {
        ""
    }
    private val singer = if (song.singer != null) {
        song.singer!!
    } else {
        ""
    }
//    private val favorite = song.favorite
    private val songLink = if (song.songLink != null) {
        song.songLink!!
    } else {
        ""
    }

    fun show() {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)   //타이틀바 제거
        dialog.setContentView(R.layout.view_more_popup)
        dialog.setCancelable(true)    //다이얼로그의 바깥 화면을 눌렀을 때 다이얼로그가 닫히게

        initialView(songName, singer)

        musicVideoButton.setOnClickListener {
            Log.d("mv", "music video")
            //MusicVideoActivity 완성되면 이렇게 연결
            val intent = Intent(
                context,
                PlayMusicVideoActivity::class.java
            ).putExtra("videoId",songLink)
            context.startActivity(intent)
        }

        favoriteButton.setOnClickListener {
            Log.d("favorite", "favorite")
//            favoriteButtonClick(favorite)
            //favorite db에 업데이트
        }

        shareButton.setOnClickListener {
            Log.d("share", "share")
        }

        dialog.show()
    }

    private fun initialView(songName: String, singer: String) {
        musicVideoButton = dialog.findViewById(R.id.musicVideoButton)
        favoriteButton = dialog.findViewById(R.id.favoriteButton)
        shareButton = dialog.findViewById(R.id.shareButton)
        songTitleName = dialog.findViewById(R.id.popupSongNameTextView)
        singerName = dialog.findViewById(R.id.popupSingerTextView)

        songTitleName.text = songName
        singerName.text = singer

//        if (favorite) {
//            favoriteButton.setBackgroundResource(R.drawable.ic_baseline_favorite_24)
//
//        } else {
//            favoriteButton.setBackgroundResource(R.drawable.ic_baseline_favorite_border_24)
//        }
    }

    private fun favoriteButtonClick(favoriteTemp: Boolean) {
        if (favoriteTemp) {
            this.favoriteTemp = false
            favoriteButton.setBackgroundResource(R.drawable.ic_baseline_favorite_border_24)

        } else {
            this.favoriteTemp = true
            favoriteButton.setBackgroundResource(R.drawable.ic_baseline_favorite_24)
        }
    }


}