package com.example.music_recomend_profile.player

import android.app.Dialog
import android.content.Context
import android.text.BoringLayout
import android.util.Log
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import com.example.music_recomend_profile.R
import com.example.music_recomend_profile.database.DataExample
import com.example.music_recomend_profile.database.RecordItem
import kotlin.properties.Delegates


class ViewMorePopup(context : Context) {
    private val dialog = Dialog(context)   //부모 액티비티의 context 가 들어감
    private lateinit var musicVideoButton : TextView
    private lateinit var favoriteButton : ImageView
    private lateinit var shareButton : ImageView
    private lateinit var songTitleName : TextView
    private lateinit var singerName : TextView
    private var favoriteTemp by Delegates.notNull<Boolean>()


    fun start(context: Context,songName:String,singer : String,favorite:Boolean) {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)   //타이틀바 제거
        dialog.setContentView(R.layout.view_more_popup)
        dialog.setCancelable(true)    //다이얼로그의 바깥 화면을 눌렀을 때 다이얼로그가 닫히게


        updateView(songName,singer)
        updateViewFavoriteButton(favorite)

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
            favoriteButtonClick(favorite)
        }

        shareButton.setOnClickListener {
            Log.d("share","share")
        }

        dialog.show()
    }

    private fun updateView(songName:String,singer : String){
        musicVideoButton = dialog.findViewById(R.id.musicVideoButton)
        favoriteButton = dialog.findViewById(R.id.favoriteButton)
        shareButton = dialog.findViewById(R.id.shareButton)
        songTitleName = dialog.findViewById(R.id.popupSongNameTextView)
        singerName = dialog.findViewById(R.id.popupSingerTextView)

        songTitleName.text = songName
        singerName.text = singer
    }

    private fun updateViewFavoriteButton(favorite: Boolean) {
        if (favorite) {
            favoriteButton.setBackgroundResource(R.drawable.ic_baseline_favorite_24)

        } else {
            favoriteButton.setBackgroundResource(R.drawable.ic_baseline_favorite_border_24)
        }
    }

    private fun favoriteButtonClick(favoriteTemp: Boolean)  {
        if (favoriteTemp) {
            this.favoriteTemp = false
            favoriteButton.setBackgroundResource(R.drawable.ic_baseline_favorite_border_24)

        } else {
            this.favoriteTemp = true
            favoriteButton.setBackgroundResource(R.drawable.ic_baseline_favorite_24)
        }
    }




}