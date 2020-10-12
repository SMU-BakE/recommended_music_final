package com.bake.recommended_music_final.player

import android.graphics.PorterDuff
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bake.recommended_music_final.R
import com.bake.recommended_music_final.TimeUtils
import com.bake.recommended_music_final.database.DataExample
import com.bake.recommended_music_final.database.RecordItem
import com.bake.recommended_music_final.database.Song
import com.bake.recommended_music_final.firebase.Initialize
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
    private var position = 0  //해당 날짜의 음악 id

    //    private lateinit var recordItem: RecordItem
    private lateinit var songList: List<Song>
    private var playingSongToggle = false

    //      about SeekBar
    private lateinit var playButton: Button
    private lateinit var nextButton: ImageView
    private lateinit var previousButton: ImageView
    private lateinit var shuffleButton: ImageView
    private lateinit var repeatButton: ImageView

    //    about youtube player
    private var youtubePlayerLoading = true
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
    private var repeatToggle = RepeatState.REPEAT

    enum class RepeatState {
        REPEAT, NO_REPEAT, ONE_SONG_REPEAT
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_home)

        intent.extras?.getInt("position")?.let {
            position = it
        }

        if (DataExample.songs == null) {
            toast("song list is empty")
            finish()
        } else {
            songList = DataExample.songs!!
        }


//        //TODO(임시 데이터 생성, 변경필요)
//        recordItem = DataExample().createRecordItem()[position]
//
//        if (DataExample().createRecordItem()[position].songList != null) {
//            songList = DataExample().createRecordItem()[position].songList!!
//        } else {
//            toast("노래 목록을 불러올 수 없습니다.")
//            finish()
//        }

        initView()
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
                        RepeatState.ONE_SONG_REPEAT -> {
                            startSong()
                        }
                        RepeatState.REPEAT -> {
                            playNext()
                        }
                        RepeatState.NO_REPEAT -> {
                            playNext()
                        }
                    }
                }
                super.onStateChange(youTubePlayer, state)
            }

            override fun onReady(youTubePlayer: YouTubePlayer) {
                youtubePlayer = youTubePlayer

                loadVideo(songList[position].songLink)

                youtubePlayerSeekBar.youtubePlayerSeekBarListener =
                    object : YouTubePlayerSeekBarListener {
                        override fun seekTo(time: Float) {
                            youtubePlayer.seekTo(time)
                        }
                    }
                youtubePlayerLoading = false
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


        //test
        val goodButton = findViewById<ImageView>(R.id.iv_good)
        goodButton.setOnClickListener {
            if (songList[position] == null) {
                toast("곡 정보를 불러올 수 없습니다.")
            } else {
                var currentSongDocId = songList[position].docId
                val starDialog = StarRatePopup(this, currentSongDocId)
                starDialog?.show()

            }

//            Initialize().increaseCondition(songList[position].docId,3)
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
            if (songList[position] == null) {
                toast("곡 정보를 불러올 수 없습니다.")
            } else {
                 var currentSong = songList[position]
                val dialog = currentSong?.let { it1 -> ViewMorePopup(this, it1) }
                dialog?.show()
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

        initPlayer()
    }

    private fun stopSong() {
        if (youtubePlayerLoading) {
            return
        }
        playButton.setBackgroundResource(R.drawable.ic_baseline_play_arrow_24)
        stopRecordAnimation()
        youtubePlayer.pause()
        playingSongToggle = false
    }

    fun startSong() {
        if (youtubePlayerLoading) {
            return
        }
        playButton.setBackgroundResource(R.drawable.ic_baseline_stop_24)
        animateRecord()
        youtubePlayer.play()
        playingSongToggle = true
        playListFragment.setHighlight(position)
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
//        dateTextView.text = recordItem.date?.let { TimeUtils().toDateString(it) }
        songNameTextView.text = songList[position].songName
        singerTextView.text = songList[position].singer
        emotionTextView.text = DataExample.myCondtion.emotion
    }

    private fun playNext() {
        if (youtubePlayerLoading) {
            return
        }
        var ended = false
        if (!shuffleToggle) {
//            normal play mode
            position += 1
            if (position > songList.size - 1) {
                position = 0
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
            position = shuffledList!![shufflePosition]
        }

        loadVideo(songList[position].songLink)
        if (ended) {
            if (repeatToggle == RepeatState.NO_REPEAT) {
                startSong()
            } else {
                stopSong()
                playListFragment.setHighlight(position)
            }
        } else {
            startSong()
        }
        updateView()
    }

    private fun playPrevious() {
        if (youtubePlayerLoading) {
            return
        }
        position -= 1
        if (position < 0) {
            position = songList.size - 1
        }

        loadVideo(songList[position].songLink)
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
        val currentPlayIndex = indexList.indexOf(position)
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
        if (repeatToggle == RepeatState.ONE_SONG_REPEAT) {
            //play one song
            repeatButton.setBackgroundResource(R.drawable.ic_baseline_repeat_24)
            repaeatButton.setColorFilter(
                ContextCompat.getColor(this, R.color.black),
                PorterDuff.Mode.SRC_IN
            )
            repeatToggle = RepeatState.REPEAT
        } else if (repeatToggle == RepeatState.REPEAT) {
            //repeatedly play all song
            repeatButton.setBackgroundResource(R.drawable.ic_baseline_repeat_24)
            repaeatButton.setColorFilter(
                ContextCompat.getColor(this, R.color.colorPrimaryDark),
                PorterDuff.Mode.SRC_IN
            )
            repeatToggle = RepeatState.NO_REPEAT
        } else {
            //repeatedly play only one song
            repeatButton.setBackgroundResource(R.drawable.ic_baseline_repeat_one_24)
            repaeatButton.setColorFilter(
                ContextCompat.getColor(this, R.color.colorPrimaryDark),
                PorterDuff.Mode.SRC_IN
            )
            repeatToggle = RepeatState.ONE_SONG_REPEAT
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        youtubePlayerView.release()
    }
}
