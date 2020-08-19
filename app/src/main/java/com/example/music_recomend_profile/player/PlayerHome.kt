package com.example.music_recomend_profile.player

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.music_recomend_profile.R
import com.example.music_recomend_profile.TimeUtils
import com.example.music_recomend_profile.database.DataExample
import com.example.music_recomend_profile.database.RecordItem
import kotlinx.android.synthetic.main.activity_player_home.*



class PlayerHome : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var recordImage: ImageView
    private lateinit var recordEmotion: TextView
    private var totalTime: Int = 0

    //미래의 이해선 DataExample recordItem companion object 로 바꾸자
    private var recordItem : RecordItem = DataExample().createRecordItem()[0]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_home)

        recordImage = findViewById(R.id.playerImage)
        recordEmotion = findViewById(R.id.emotionTextView)



        moreViewButton.setOnClickListener {
            val dialog = ViewMorePopup(this)
            recordItem.songList?.get(0)?.songName?.let { it1 ->
                recordItem.songList?.get(0)?.singer?.let { it2 ->
                    recordItem.songList?.get(0)?.favorite?.let { it3 ->
                        dialog.start(this
                            , it1
                            , it2
                            , it3
                        )
                    }
                }
            }
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

    override fun onStart() {
        super.onStart()
        getRecordIntent()
        updateView()
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
        recordEmotion.animation = rotateText
    }

    private fun stopRecordAnimation() {
        recordImage.clearAnimation()
        recordEmotion.clearAnimation()
    }

    private fun getRecordIntent() {

        intent.extras?.getString("emotion")?.let {
            if (it.isNotEmpty()) {
                recordItem.emotion = it
            }
        }

        intent.extras?.getString("song")?.let {
            if (it.isNotEmpty()) {
                recordItem.songList?.get(0)?.songName = it
            }
        }

        intent.extras?.getString("singer")?.let {
            if (it.isNotEmpty()) {
                recordItem.songList?.get(0)?.singer = it
            }
        }

        intent.extras?.getLong("date")?.let {
            recordItem.date = it
        }

        intent.extras?.getBoolean("favorite")?.let {
            recordItem.songList?.get(0)?.favorite = it
        }
    }

    private fun updateView(){
        dateTextView.text = recordItem.date?.let { TimeUtils().toDateString(it) }
        singerTextView.text = recordItem.songList?.get(0)?.singer
        songNameTextView.text = recordItem.songList?.get(0)?.songName
        emotionTextView.text = recordItem.emotion
    }


}
