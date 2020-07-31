package com.example.music_recomend_profile.database

class RecordItem {

    var title: String? = null
    var date: Long? = null

    constructor(title:String?, date:Long) {
        this.title = title
        this.date = date
    }
}
