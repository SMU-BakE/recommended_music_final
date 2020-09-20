package com.bake.recommended_music_final.database

import android.util.Log

data class RecordItem(
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

data class Condition(
    var emotion: String = "flutter",
    var weather: String = "cloudy",
    var season: String = "fall",
    var time: String = "morning"
)

data class ConditionNotUsed(
    var angry: Int = 0,
    var flutter: Int = 0,
    var funny: Int = 0,
    var happy: Int = 0,
    var sad: Int = 0,
    var soso: Int = 0,

    var sunshine: Int = 0,
    var cloudy: Int = 0,
    var rainy: Int = 0,
    var hot: Int = 0,
    var cold: Int = 0,
    var snowy: Int = 0,

    var spring: Int = 0,
    var summer: Int = 0,
    var fall: Int = 0,
    var winter: Int = 0,

    var morning: Int = 0,
    var lunch: Int = 0,
    var evening: Int = 0,
    var dawn: Int = 0

)






