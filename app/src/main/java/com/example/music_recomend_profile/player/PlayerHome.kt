package com.example.music_recomend_profile.player

import android.graphics.PorterDuff
import android.os.Bundle
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
    //      About view widget
    private lateinit var recordImage: ImageView
    private lateinit var recordEmotion: TextView

    //      about playing music
    private var position by Delegates.notNull<Int>()    //해당 날짜의 음악 id
    private lateinit var recordItem: RecordItem
    private var songPosition = 0
    private lateinit var songList: List<Song>
    private var playingSongToggle = false

    //      about SeekBar
    private lateinit var playButton: Button
    private lateinit var nextButton: ImageView
    private lateinit var previousButton: ImageView
    private lateinit var shuffleButton: ImageView
    private lateinit var repeatButton: ImageView

    //    about youtube player
    private lateinit var youtubePlayer: YouTubePlayer
    private lateinit var youtubePlayerView: YouTubePlayerView
    private lateinit var youtubePlayerSeekBar: YouTubePlayerSeekBar

    //    about playlist
    private lateinit var playListFragment: PlayList
    private var queue = false

    //     about shuffle
    private var shuffleToggle = false
    private var shuffledList: ArrayList<Int>? = null
    private var shufflePosition = 0

    //      about repeat
    private var repeatToggle: Int = 1

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
                    when (repeatToggle) {
                        0 -> {
                            startSong()
                        }
                        1 -> {
                            playNext()
                        }
                        2 -> {
                            playNext()
                        }
                    }
                }
                super.onStateChange(youTubePlayer, state)
            }

            override fun onReady(youTubePlayer: YouTubePlayer) {
                youtubePlayer = youTubePlayer

                val currentVideoId = songList[songPosition].songLink

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

        repeatButton = findViewById(R.id.repaeatButton)
        repaeatButton.setOnClickListener {
            playRepeat()
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
            recordItem.songList?.get(songPosition)?.songName?.let { it1 ->
                recordItem.songList?.get(songPosition)?.singer?.let { it2 ->
                    recordItem.songList?.get(songPosition)?.favorite?.let { it3 ->
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
            if (playingSongToggle) {
                stopSong()
            } else {
                startSong()
            }
        }
        setShuffle()
        playRepeat()
    }

    private fun stopSong() {
        playButton.setBackgroundResource(R.drawable.ic_baseline_play_arrow_24)
        stopRecordAnimation()
        youtubePlayer.pause()
        playingSongToggle = false
    }

    private fun startSong() {
        playButton.setBackgroundResource(R.drawable.ic_baseline_stop_24)
        animateRecord()
        youtubePlayer.play()
        playingSongToggle = true
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
        singerTextView.text = recordItem.songList?.get(songPosition)?.singer
        songNameTextView.text = recordItem.songList?.get(songPosition)?.songName
        emotionTextView.text = recordItem.emotion
    }

    private fun playNext() {
        var ended = false
        if (!shuffleToggle) {
//            normal play mode
            songPosition += 1
            if (songPosition > songList.size - 1) {
                songPosition = 0
                ended = true
            }
        } else {
//            shuffle play mode
            if (shuffledList == null) {
                toast("랜덤 재생 목록을 불러올 수 없습니다.")
                finish()
            }
            shufflePosition += 1
            if (shufflePosition > shuffledList!!.size - 1) {
                shufflePosition = 0
                ended = true
            }
            songPosition = shuffledList!![shufflePosition]
        }

        val videoId = songList[songPosition].songLink
        videoId?.let { loadVideo(it) }
        if (ended) {
            if (repeatToggle == 2) {
                startSong()
            }else{
                stopSong()
            }
        } else {
            startSong()
        }
        updateView()

        /*songPosition += 1
        if (songPosition > songList.size - 1) {
            when (repeatToggle) {
                1 -> {
                    stopSong()
                }
                2 -> songPosition = 0
            }
        }

        if (shuffleToggle) {
            if (shuffledList == null) {
                toast("랜덤 재생 목록을 불러올 수 없습니다.")
                finish()
            }

            shufflePosition += 1
            if (shufflePosition > shuffledList!!.size - 1) {
                when (repeatToggle) {
                    1 -> {
                        stopSong()
                    }
                    2 -> shufflePosition = 0
                }
            }
            songPosition = shuffledList!![shufflePosition]
        }

        if (songPosition != songList.size || repeatToggle == 2) {
            val videoId = songList[songPosition].songLink
            videoId?.let { loadVideo(it) }
            startSong()
            updateView()
        } else {
            songPosition = -1
        }*/
    }

    private fun playPrevious() {
        songPosition -= 1
        if (songPosition < 0) {
            songPosition = songList.size - 1
        }

        val videoId = songList[songPosition].songLink
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
        val currentPlayIndex = indexList.indexOf(songPosition)
        val willChange = indexList[0]
        indexList[0] = indexList[currentPlayIndex]
        indexList[currentPlayIndex] = willChange

        shuffledList = indexList
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
            //Log.d("randomize", randomIndexList.toString())
        }
    }

    private fun playRepeat() {
        if (repeatToggle == 0) {
            //play one song
            repeatButton.setBackgroundResource(R.drawable.ic_baseline_repeat_24)
            repaeatButton.setColorFilter(
                ContextCompat.getColor(this, R.color.black),
                PorterDuff.Mode.SRC_IN
            )
            repeatToggle = 1
        } else if (repeatToggle == 1) {
            //repeatedly play all song
            repeatButton.setBackgroundResource(R.drawable.ic_baseline_repeat_24)
            repaeatButton.setColorFilter(
                ContextCompat.getColor(this, R.color.colorPrimaryDark),
                PorterDuff.Mode.SRC_IN
            )
            repeatToggle = 2
        } else {
            //repeatedly play only one song
            repeatButton.setBackgroundResource(R.drawable.ic_baseline_repeat_one_24)
            repaeatButton.setColorFilter(
                ContextCompat.getColor(this, R.color.colorPrimaryDark),
                PorterDuff.Mode.SRC_IN
            )
            repeatToggle = 0
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        youtubePlayerView.release()
    }
}
