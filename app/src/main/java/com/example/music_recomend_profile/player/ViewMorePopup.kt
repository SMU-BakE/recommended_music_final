package com.example.music_recomend_profile.player

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.music_recomend_profile.R
import com.example.music_recomend_profile.database.DataExample
import com.example.music_recomend_profile.database.Song
import com.example.music_recomend_profile.setting.SettingListActivity
import kotlinx.android.synthetic.main.activity_player_home.*


class ViewMorePopup(context : Context) {
    private val dialog = Dialog(context)   //부모 액티비티의 context 가 들어감
    private lateinit var musicVideoButton : TextView
    private lateinit var favoriteButton : ImageView
    private lateinit var shareButton : ImageView

    var song : ArrayList<Song> = DataExample().createSong()


    fun start(context: Context) {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)   //타이틀바 제거
        dialog.setContentView(R.layout.view_more_popup)
        dialog.setCancelable(true)    //다이얼로그의 바깥 화면을 눌렀을 때 다이얼로그가 닫히게


        musicVideoButton = dialog.findViewById(R.id.musicVideoButton)
        favoriteButton = dialog.findViewById(R.id.favoriteButton)
        shareButton = dialog.findViewById(R.id.shareButton)

        updateViewFavoriteButton()

        musicVideoButton.setOnClickListener {
            Log.d("mv","music video")

            //MusicVideoActivity 완성되면 이렇게 연결
            /*val intent = Intent(
                context,
                MusicVideo::class.java
            )
            context.startActivity(intent)*/
        }

        favoriteButton.setOnClickListener {
            Log.d("favorite","favorite")
            favoriteButtonClick()
        }

        shareButton.setOnClickListener {
            Log.d("share","share")
        }

        dialog.show()
    }

    private fun updateViewFavoriteButton() {
        if (song[0].favorite) {
            favoriteButton.setBackgroundResource(R.drawable.ic_baseline_favorite_24)

        } else {
            favoriteButton.setBackgroundResource(R.drawable.ic_baseline_favorite_border_24)
        }
    }

    private fun favoriteButtonClick() {
        if (song[0].favorite) {
            song[0].favorite = false
            favoriteButton.setBackgroundResource(R.drawable.ic_baseline_favorite_border_24)

        } else {
            song[0].favorite= true
            favoriteButton.setBackgroundResource(R.drawable.ic_baseline_favorite_24)
        }
    }


}