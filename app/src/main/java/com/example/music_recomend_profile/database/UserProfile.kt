package com.example.music_recomend_profile.database

data class UserProfile(
    var name: String? = null,
    var email: String? = null,
    var follower: Int? = null,
    var following: Int? = null,
    var introduce: String? = null,
    var age: Int? = 0,
    var sex: String? = null,
    var birthday: Long? = 0,

    //이미지 어떻게 받는지에따라 변수 변경해야함.
    var imageSrc: String? = null
)