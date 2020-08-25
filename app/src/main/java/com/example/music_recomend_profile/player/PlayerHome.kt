package com.example.music_recomend_profile.player

import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
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
import java.util.*
import kotlin.collections.ArrayList
import kotlin.properties.Delegates


class PlayerHome : AppCompatActivity() {


    private var playingSong = false
    private var queue = false
    private var shuffleToggle = false

    // About view widget
    private lateinit var recordImage: ImageView
    private lateinit var recordEmotion: TextView

    private var position by Delegates.notNull<Int>()

    private lateinit var recordItem: RecordItem

    private var songIndex = 0
    private lateinit var songList: List<Song>

    //    about SeekBar
    private lateinit var playButton: Button
    private lateinit var nextButton: ImageView
    private lateinit var previousButton: ImageView
    private lateinit var shuffleButton: ImageView

    //    about youtube player
    private lateinit var youtubePlayer: YouTubePlayer
    private lateinit var youtubePlayerView: YouTubePlayerView
    private lateinit var youtubePlayerSeekBar: YouTubePlayerSeekBar

    //    about playlist
    private lateinit var playListFragment: PlayList

    //about shuffle
    private var randomIndexList: ArrayList<Int>? = null

    private var shufflePosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_home)

        intent.extras?.getInt("position")?.let {
            position = it
        }

        //TODO(임시 데이터 생성, 변경필요)
        recordItem = DataExample().createRecordItem()[position]

        if (DataExample().createRecordItem()[position].songList != null) {
            songList = DataExample().createRecordItem()[position].songList!!
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

            override fun onReady(youTubePlayer: YouTubePlayer) {
                youtubePlayer = youTubePlayer

                val currentVideoId = songList[songIndex].songLink

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



        shuffleButton = findViewById(R.id.shuffleButton)
        shuffleButton.setOnClickListener {
            setShuffle()
        }

        playListFragment = PlayList()
        supportFragmentManager.beginTransaction()
            .add(R.id.songListContainer, playListFragment)
            .hide(playListFragment)
            .commit()

        queueMusicButton.setOnClickListener {
            if (!queue) {
                supportFragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.dropdown, R.anim.dropdown)
                    .show(playListFragment)
                    .commit()
                queue = true
            } else {
                supportFragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.riseup, R.anim.riseup)
                    .hide(playListFragment).commit()
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

        setShuffle()

    }

    private fun stopSong() {
        playButton.setBackgroundResource(R.drawable.ic_baseline_play_arrow_24)
        stopRecordAnimation()
        youtubePlayer.pause()
        playingSong = false
    }

    private fun startSong() {
        playButton.setBackgroundResource(R.drawable.ic_baseline_stop_24)
        animateRecord()
        youtubePlayer.play()
        playingSong = true
    }


    override fun onStart() {
        super.onStart()
        updateView()
    }

    override fun onRestart() {
        super.onRestart()
        stopSong()
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

    private fun updateView() {
        dateTextView.text = recordItem.date?.let { TimeUtils().toDateString(it) }
        singerTextView.text = recordItem.songList?.get(songIndex)?.singer
        songNameTextView.text = recordItem.songList?.get(songIndex)?.songName
        emotionTextView.text = recordItem.emotion
    }

    private fun playNext() {
        songIndex += 1
        if (songIndex > songList.size - 1) {
            songIndex = 0
        }

        if (shuffleToggle) {
            if (randomIndexList == null) {
                toast("랜덤 재생 목록을 불러올 수 없습니다.")
                finish()
            }

            shufflePosition += 1
            if (shufflePosition > randomIndexList!!.size - 1) {
                shufflePosition = 0
            }

            songIndex = randomIndexList!![shufflePosition]
        }

        val videoId = songList[songIndex].songLink

        videoId?.let { loadVideo(it) }
        startSong()
        updateView()
    }

    private fun playPrevious() {
        songIndex -= 1
        if (songIndex < 0) {
            songIndex = songList.size - 1
        }

        val videoId = songList[songIndex].songLink
        videoId?.let { loadVideo(it) }
        startSong()
        updateView()
    }


    private fun createRandomIndexList() {
        val listLength = songList.size - 1

        val indexList = arrayListOf<Int>()
        songList.mapIndexed { i, value ->
            indexList.add(i)
        }

        val random = Random()

        indexList.mapIndexed { index, song ->
            if (listLength == index) {
                return@mapIndexed
            }
//            create Random position
            val randomNum = random.nextInt(listLength - index)
//            will exchange random position n last position - index
            val currentPosition = indexList[randomNum]

            indexList[randomNum] = indexList[listLength - index]
            indexList[listLength - index] = currentPosition
        }

//        set first value to current song
        val currentPlayIndex = indexList.indexOf(songIndex)
        val willChange = indexList[0]
        indexList[0] = indexList[currentPlayIndex]
        indexList[currentPlayIndex] = willChange

        randomIndexList = indexList
        shufflePosition = 0
    }

    private fun setShuffle() {
        if (shuffleToggle) {
//            unset shuffle
            shuffleButton.setColorFilter(
                ContextCompat.getColor(this, R.color.black),
                PorterDuff.Mode.SRC_IN
            )
            shuffleToggle = false
        } else {
//            set shuffle
            shuffleButton.setColorFilter(
                ContextCompat.getColor(this, R.color.colorPrimaryDark),
                PorterDuff.Mode.SRC_IN
            )
            shuffleToggle = true
            createRandomIndexList()
            Log.d("randomize", randomIndexList.toString())
        }

    }

    private fun playRepeat() {

    }

    override fun onDestroy() {
        super.onDestroy()
        youtubePlayerView.release()
    }
}
