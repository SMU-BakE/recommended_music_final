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
//        functions.useEmulator("10.0.2.2", 5001)
    }

    fun sampleLike(
        songDocId: String,
        emotion: String,
        weather: String,
        season: String,
        time: String
    ) {
        Log.d("start fx", "fuck")
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


    fun callRecommendMusic(emotion: String, weather: String, heartRate: Int?) {
        val request = hashMapOf<String, String>(
            "emotion" to emotion,
            "weather" to weather
        )
        functions.getHttpsCallable("classifySong").call(request).addOnCompleteListener {
            val result = it.result?.data.toString()
            Log.d("result", result)
        }
    }

    fun callIncreaseCount(songName: String, condition: String, preference: String) {
        val request = hashMapOf<String, String>(
            "message" to songName + condition + preference
        )
        functions.getHttpsCallable("increaseCondition").call(request).addOnCompleteListener {
            val result = it.result?.data.toString()
            Log.d("result", result)
        }
    }

    fun callDecreaseCount(songName: String, condition: String, preference: String) {
        val request = hashMapOf<String, String>(
            "message" to songName + condition + preference
        )
        functions.getHttpsCallable("decreaseCondition").call(request).addOnCompleteListener {
            val result = it.result?.data.toString()
            Log.d("result", result)
        }
    }
}