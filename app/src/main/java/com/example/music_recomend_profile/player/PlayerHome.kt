package com.example.music_recomend_profile.player

import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.music_recomend_profile.R
import com.example.music_recomend_profile.TimeUtils
import com.example.music_recomend_profile.database.DataExample
import com.example.music_recomend_profile.database.RecordItem
import com.example.music_recomend_profile.database.Song
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.ui.views.YouTubePlayerSeekBar
import com.pierfrancescosoffritti.androidyoutubeplayer.core.ui.views.YouTubePlayerSeekBarListener
import kotlinx.android.synthetic.main.activity_player_home.*
import org.jetbrains.anko.toast
import kotlin.properties.Delegates


class PlayerHome : AppCompatActivity() {


    private var playingSong = false
    private var queue = false

    // About view widget
    private lateinit var recordImage: ImageView
    private lateinit var recordEmotion: TextView

    private var position by Delegates.notNull<Int>()

    private lateinit var recordItem: RecordItem

    private var songIndex = 0
    private lateinit var songCodeList: List<Song>

    //    about SeekBar
    private lateinit var playButton: Button
    private lateinit var nextButton: ImageView
    private lateinit var previousButton: ImageView

    //    about youtube player
    private lateinit var youtubePlayer: YouTubePlayer
    private lateinit var youtubePlayerView: YouTubePlayerView
    private lateinit var youtubePlayerSeekBar: YouTubePlayerSeekBar

    //    about playlist
    private lateinit var playListFragement: PlayList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_home)

        getRecordIntent()

        if (DataExample().createRecordItem()[position].songList != null) {
            songCodeList = DataExample().createRecordItem()[position].songList!!
        } else {
            toast("노래 목록을 불러올 수 없습니다.")
            finish()
        }

        initPlayer()
    }


    private fun initPlayer() {
        youtubePlayerView = findViewById(R.id.youtube_player_view)
        youtubePlayerSeekBar = findViewById(R.id.youtube_player_seekbar)

        youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onStateChange(
                youTubePlayer: YouTubePlayer,
                state: PlayerConstants.PlayerState
            ) {
                if (state == PlayerConstants.PlayerState.ENDED) {
                    playNext()
                }
                super.onStateChange(youTubePlayer, state)
            }

            override fun onReady(player: YouTubePlayer) {
                youtubePlayer = player

                val currentVideoId = songCodeList?.get(songIndex)?.songLink

                currentVideoId?.let { loadVideo(it) }

                youtubePlayerSeekBar.youtubePlayerSeekBarListener =
                    object : YouTubePlayerSeekBarListener {
                        override fun seekTo(time: Float) {
                            youtubePlayer.seekTo(time)
                        }
                    }

                initView()
            }


        })
    }

    private fun loadVideo(videoId: String) {
        youtubePlayer.loadVideo(videoId, 0f)
        youtubePlayer.addListener(youtubePlayerSeekBar)
        youtubePlayer.pause()
    }


    private fun initView() {
        recordImage = findViewById(R.id.playerImage)
        recordEmotion = findViewById(R.id.emotionTextView)

        backButton.setOnClickListener {
            onBackPressed()
        }

        nextButton = findViewById(R.id.nextSongButton)
        nextButton.setOnClickListener {
            playNext()
        }

        previousButton = findViewById(R.id.previousSongButton)
        previousButton.setOnClickListener {
            playPrevious()
        }

        playListFragement = PlayList()
        supportFragmentManager.beginTransaction()
            .add(R.id.songListContainer, playListFragement)
            .hide(playListFragement)
            .commit()
        queueMusicButton.setOnClickListener {
            if (!queue) {
                supportFragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.dropdown, R.anim.dropdown)
                    .show(playListFragement)
                    .commit()
                queue = true
            } else {
                supportFragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.riseup, R.anim.riseup)
                    .hide(playListFragement).commit()
                queue = false
            }
        }

        moreViewButton.setOnClickListener {
            val dialog = ViewMorePopup(this)
            recordItem.songList?.get(songIndex)?.songName?.let { it1 ->
                recordItem.songList?.get(songIndex)?.singer?.let { it2 ->
                    recordItem.songList?.get(songIndex)?.favorite?.let { it3 ->
                        dialog.start(
                            this
                            , it1
                            , it2
                            , it3
                        )
                    }
                }
            }
        }

        playButton = findViewById(R.id.playButton)
        playButton.setOnClickListener {
            if (playingSong) {
                stopSong()
            } else {
                startSong()
            }
        }
    }

    private fun stopSong() {
        playButton.setBackgroundResource(R.drawable.ic_baseline_play_arrow_24)
        stopRecordAnimation()
        stopVideo()
    }

    private fun startSong() {
        playButton.setBackgroundResource(R.drawable.ic_baseline_stop_24)
        animateRecord()
        startVideo()
    }

    private fun startVideo() {
        youtubePlayer.play()
        playingSong = true
    }

    private fun stopVideo() {
        youtubePlayer.pause()
        playingSong = false
    }

    override fun onStart() {
        super.onStart()
        updateView()
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
        intent.extras?.getInt("position")?.let {
            position = it
        }

        //임시 데이터 생성
        recordItem = DataExample().createRecordItem()[position]
    }


    private fun updateView() {
        dateTextView.text = recordItem.date?.let { TimeUtils().toDateString(it) }
        singerTextView.text = recordItem.songList?.get(songIndex)?.singer
        songNameTextView.text = recordItem.songList?.get(songIndex)?.songName
        emotionTextView.text = recordItem.emotion
    }

    private fun playNext() {
        songIndex += 1
        if (songIndex > songCodeList.size - 1) {
            songIndex = 0
        }

        val videoId = songCodeList[songIndex].songLink
        videoId?.let { loadVideo(it) }
        startSong()
        updateView()
    }

    private fun playPrevious() {
        songIndex -= 1
        if (songIndex < 0) {
            songIndex = songCodeList.size - 1
        }

        val videoId = songCodeList[songIndex].songLink
        videoId?.let { loadVideo(it) }
        startSong()
        updateView()
    }

    override fun onDestroy() {
        super.onDestroy()
        youtubePlayerView.release()
    }
}
