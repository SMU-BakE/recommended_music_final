package com.bake.recommended_music_final.firebase

import android.util.Log
import com.bake.recommended_music_final.database.*
import com.google.android.gms.tasks.Task
import com.google.firebase.functions.FirebaseFunctions
import com.google.firebase.functions.ktx.functions
import com.google.firebase.ktx.Firebase
import kotlin.random.Random

class Initialize {
    private var functions: FirebaseFunctions = Firebase.functions("asia-northeast3")

    init {
//        functions.useEmulator("10.0.2.2", 5001)
    }


    //초반에 데이터 모으기 위해 랜덤지정
    fun randomWeather(): String {
        val random = Random
        val num = random.nextInt(14)

        val conditionList = listOf<String>(
            "cloudy", "sunshine", "rainy", "snowy", "hot", "cold",
            "fall", "spring", "winter", "summer",
            "morning", "lunch", "evening", "dawn"
        )

        return conditionList[num]
    }

    fun increaseCondition(
        songDocId: String,
        starRate: Int
    ) {
        val condition = DataExample.myCondtion
        Log.d("start fx", "increase")
        val request = ConditionFeedbackRequest(songDocId, condition, starRate)

//        val request = hashMapOf<String, Any>(
//            "songDocId" to songDocId, "condition" to conditionRequest, "star"
//        )

        functions.getHttpsCallable("increaseCondition").call(request.toMap())
            .addOnCompleteListener {
                if (!it.isSuccessful) {
                    Log.e("error", it.exception.toString())
                    return@addOnCompleteListener
                }

                Log.d("result", it.result.toString())
            }
    }

    /*fun decreaseCondition(
        songDocId: String
    ) {
        Log.d("start fx", "decrease")
        val condition = DataExample.myCondtion

        val conditionRequest = hashMapOf<String, String>(
            "emotion" to condition.emotion,
            "weather" to condition.weather,
            "season" to condition.season,
            "time" to condition.time
        )
        val request = hashMapOf<String, Any>(
            "songDocId" to songDocId, "condition" to conditionRequest
        )

        functions.getHttpsCallable("decreaseCondition").call(request).addOnCompleteListener {
            if (!it.isSuccessful) {
                Log.e("error", it.exception.toString())
                return@addOnCompleteListener
            }

            Log.d("result", it.result.toString())
        }
    }*/


    fun callRecommendMusic(
        condition: Condition
    ): Task<List<Song>> {
        val emotion = condition.emotion
        val weather = condition.weather
        val season = condition.season
        val time = condition.time
        val heartRate = condition.heartRate

        Log.d("recommend music fx is requested", "request")

        val request = ConditionRequest(Condition(emotion, weather, season, time, heartRate))

        return functions.getHttpsCallable("requestSongListWithCondition").call(request.toMap())
            .continueWith {
                if (!it.isSuccessful) {
                    throw Error("hello?")
                }

                val songs = it.result?.data as List<*>

                val songList: ArrayList<Song> = arrayListOf()

                songs.map { song ->
                    val songData = Song().from(song as HashMap<String, *>)
                    if (songData != null) {
                        songList.add(songData)
                    }
                }
//                Log.d("result", songs.toString())
                return@continueWith songList
            }
    }


}