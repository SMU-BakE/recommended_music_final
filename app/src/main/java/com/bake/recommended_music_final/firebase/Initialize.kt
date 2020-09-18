package com.bake.recommended_music_final.firebase

import android.util.Log
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.functions.FirebaseFunctions
import com.google.firebase.functions.ktx.functions
import com.google.firebase.ktx.Firebase

class Initialize {
    private var functions: FirebaseFunctions = Firebase.functions

    init {
        functions.useEmulator("10.0.2.2", 5001)
    }


    fun callRecommendMusic(emotion: String, weather: String, heartRate: Int?) {
        val request = hashMapOf<String, String>(
            "message" to emotion + weather + heartRate
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