package com.bake.recommended_music_final.firebase

import android.util.Log
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.functions.FirebaseFunctions
import com.google.firebase.functions.ktx.functions
import com.google.firebase.ktx.Firebase

class Initialize {
    private var functions: FirebaseFunctions = Firebase.functions("asia-northeast3")

    init {
        functions.useEmulator("10.0.2.2", 5001)
    }

    fun increaseCondition(
        songDocId: String,
        emotion: String,
        weather: String,
        season: String,
        time: String
    ) {
        Log.d("start fx", "increase")
        val conditionRequest = hashMapOf<String, String>(
            "emotion" to emotion,
            "weather" to weather,
            "season" to season,
            "time" to time
        )
        val request = hashMapOf<String, Any>(
            "songDocId" to songDocId, "condition" to conditionRequest
        )

        functions.getHttpsCallable("increaseCondition").call(request).addOnCompleteListener {
            if (!it.isSuccessful) {
                Log.e("error", it.exception.toString())
                return@addOnCompleteListener
            }

            Log.d("result", it.result.toString())
        }
    }

    fun decreaseCondition(
        songDocId: String,
        emotion: String,
        weather: String,
        season: String,
        time: String
    ) {
        Log.d("start fx", "decrease")
        val conditionRequest = hashMapOf<String, String>(
            "emotion" to emotion,
            "weather" to weather,
            "season" to season,
            "time" to time
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
    }


    fun callRecommendMusic(emotion: String, weather: String, season: String, time: String) {
        val request = hashMapOf<String, String>(
            "emotion" to emotion,
            "weather" to weather,
            "season" to season,
            "time" to time
        )
        functions.getHttpsCallable("requestSongListWithCondition").call(request)
            .addOnCompleteListener {
                val result = it.result?.data.toString()
                Log.d("result", result)
            }
    }


}