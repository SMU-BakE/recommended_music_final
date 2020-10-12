package com.bake.recommended_music_final.player

import android.app.Dialog
import android.content.Context
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import com.bake.recommended_music_final.R
import com.bake.recommended_music_final.firebase.Initialize


class StarRatePopup(val context: Context, songDocId: String) {
    private val starDialog = Dialog(context)   //부모 액티비티의 context 가 들어감

    private lateinit var star1Button: ImageView
    private lateinit var star2Button: ImageView
    private lateinit var star3Button: ImageView
    private lateinit var star4Button: ImageView
    private lateinit var star5Button: ImageView
    private lateinit var completeButton: TextView


    private var starRate: Int = 0

    private val songDocId = if (songDocId != null) {
        songDocId!!
    } else {
        ""
    }

    private fun initView() {
        star1Button = starDialog.findViewById(R.id.star1)
        star2Button = starDialog.findViewById(R.id.star2)
        star3Button = starDialog.findViewById(R.id.star3)
        star4Button = starDialog.findViewById(R.id.star4)
        star5Button = starDialog.findViewById(R.id.star5)
        completeButton = starDialog.findViewById(R.id.starRateComplete)
    }

    private fun initStar() {
        star1Button.setBackgroundResource(R.drawable.ic_baseline_star_border_24)
        star2Button.setBackgroundResource(R.drawable.ic_baseline_star_border_24)
        star3Button.setBackgroundResource(R.drawable.ic_baseline_star_border_24)
        star4Button.setBackgroundResource(R.drawable.ic_baseline_star_border_24)
        star5Button.setBackgroundResource(R.drawable.ic_baseline_star_border_24)

    }

    fun show() {
        starDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)   //타이틀바 제거
        starDialog.setContentView(R.layout.activity_star_rate_popup)
        starDialog.setCancelable(true)    //다이얼로그의 바깥 화면을 눌렀을 때 다이얼로그가 닫히게
//        Toast.makeText(context, songDocId, Toast.LENGTH_SHORT).show()

        initView()

        star1Button.setOnClickListener {
            initStar()
            starRate = -2
            star1Button.setBackgroundResource(R.drawable.ic_baseline_star_24)
        }

        star2Button.setOnClickListener {
            initStar()
            starRate = -1
            star1Button.setBackgroundResource(R.drawable.ic_baseline_star_24)
            star2Button.setBackgroundResource(R.drawable.ic_baseline_star_24)
        }

        star3Button.setOnClickListener {
            initStar()
            starRate = 0
            star1Button.setBackgroundResource(R.drawable.ic_baseline_star_24)
            star2Button.setBackgroundResource(R.drawable.ic_baseline_star_24)
            star3Button.setBackgroundResource(R.drawable.ic_baseline_star_24)
        }

        star4Button.setOnClickListener {
            initStar()
            starRate = 1
            star1Button.setBackgroundResource(R.drawable.ic_baseline_star_24)
            star2Button.setBackgroundResource(R.drawable.ic_baseline_star_24)
            star3Button.setBackgroundResource(R.drawable.ic_baseline_star_24)
            star4Button.setBackgroundResource(R.drawable.ic_baseline_star_24)
        }

        star5Button.setOnClickListener {
            initStar()
            starRate = 2
            star1Button.setBackgroundResource(R.drawable.ic_baseline_star_24)
            star2Button.setBackgroundResource(R.drawable.ic_baseline_star_24)
            star3Button.setBackgroundResource(R.drawable.ic_baseline_star_24)
            star4Button.setBackgroundResource(R.drawable.ic_baseline_star_24)
            star5Button.setBackgroundResource(R.drawable.ic_baseline_star_24)
        }

        completeButton.setOnClickListener {
            Initialize().increaseCondition(songDocId, starRate)
            starDialog.cancel()
        }



        starDialog.show()
    }


}