package com.example.music_recomend_profile.player

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.music_recomend_profile.R
import kotlinx.android.synthetic.main.activity_player_home.*
import kotlinx.android.synthetic.main.view_more_popup.*
import org.jetbrains.anko.appcompat.v7.buttonBarLayout
import org.jetbrains.anko.toast

class PlayerHome : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var recordImage: ImageView
    private lateinit var recordText: TextView
    private var totalTime: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_home)

        recordImage = findViewById(R.id.playerImage)
        recordText = findViewById(R.id.emotionTextView)

        moreViewButton.setOnClickListener {
            val dialog = ViewMorePopup(this)
            dialog.start(this)
        }




        mediaPlayer = MediaPlayer.create(this, R.raw.music)
        mediaPlayer.isLooping = true
        mediaPlayer.setVolume(0.5f, 0.5f)
        totalTime = mediaPlayer.duration


        // Position Bar
        positionBar.max = totalTime
        positionBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    if (fromUser) {
                        mediaPlayer.seekTo(progress)
                    }
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {
                }

                override fun onStopTrackingTouch(p0: SeekBar?) {
                }
            }
        )

        // Thread
        Thread(Runnable {
            while (mediaPlayer != null) {
                try {
                    var msg = Message()
                    msg.what = mediaPlayer.currentPosition
                    handler.sendMessage(msg)
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                }
            }
        }).start()
    }

    @SuppressLint("HandlerLeak")
    var handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            var currentPosition = msg.what

            // Update positionBar
            positionBar.progress = currentPosition

            // Update Labels
            var elapsedTime = createTimeLabel(currentPosition)
            elapsedTimeLabel.text = elapsedTime

            var remainingTime = createTimeLabel(totalTime)
            remainingTimeLabel.text = "$remainingTime"
        }
    }

    fun createTimeLabel(time: Int): String {
        var timeLabel = ""
        var min = time / 1000 / 60
        var sec = time / 1000 % 60

        timeLabel = "$min:"
        if (sec < 10) timeLabel += "0"
        timeLabel += sec

        return timeLabel
    }

    fun playButtonClick(v: View) {

        if (mediaPlayer.isPlaying) {
            // Stop
            mediaPlayer.pause()
            playButton.setBackgroundResource(R.drawable.ic_baseline_play_arrow_24)
            stopRecordAnimation()

        } else {
            // Start
            mediaPlayer.start()
            playButton.setBackgroundResource(R.drawable.ic_baseline_stop_24)
            animateRecord()
        }
    }

    private fun animateRecord() {
        val rotateRecord = AnimationUtils.loadAnimation(this, R.anim.record_animation)
        recordImage.animation = rotateRecord
        val rotateText = AnimationUtils.loadAnimation(this, R.anim.record_animation)
        recordText.animation = rotateText
    }

    private fun stopRecordAnimation() {
        recordImage.clearAnimation()
        recordText.clearAnimation()
    }


}