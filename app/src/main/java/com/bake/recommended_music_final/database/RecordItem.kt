package com.bake.recommended_music_final.database

import android.util.Log

data class RecordItem(
    var userId: String = "",
    var emotion: String? = null,
    var date: Long? = null,
    var songList: List<Song>? = null
)

data class Song(
    var songName: String = "",
    var singer: String = "",
    var songLink: String = "",
    var docId: String = ""
) {
    fun from(map: HashMap<String, *>): Song? {
        val songName = map["songName"]
        val singer = map["singer"]
        val songLink = map["songLink"]
        val docId = map["docId"]
        return if (songName is String && singer is String && songLink is String && docId is String) {
            Song(songName, singer, songLink, docId)
        } else {
            Log.e("db error", "cannot parse Song object")
            null
        }
    }
}

data class ConditionFeedbackRequest(
    var songDocId: String,
    var condition: Condition,
    var starRate: Int
) {
    fun toMap(): Map<String, Any> {
        return mapOf(
            "songDocId" to songDocId,
            "condition" to condition.toMap(),
            "starRate" to starRate
        )
    }
}

data class ConditionRequest(var condition: Condition) {
    fun toMap(): Map<String, String> {
        return condition.toMap()
    }
}

data class Condition(
    var emotion: String = "flutter",
    var weather: String = "cloudy",
    var season: String = "fall",
    var time: String = "morning",
    var heartRate: String = "normalHeartRate"
) {
    fun toMap(): Map<String, String> {
        return hashMapOf<String, String>(
            "emotion" to emotion,
            "weather" to weather,
            "season" to season,
            "time" to time,
            "heartRate" to heartRate
        )
    }
}








