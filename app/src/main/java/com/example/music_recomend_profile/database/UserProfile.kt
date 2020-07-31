package com.example.music_recomend_profile.database

class UserProfile {
    var name: String? = null
    var email:String? = null
    var image: Int? = 0
    var follower: Int? = null
    var following: Int? = null
    var introduce: String? = null
    var age: Int? = 0
    var sex: String? = null


    constructor(
        name: String?, email:String?,image: Int?, follower: Int?, following: Int?, introduce: String?
        , age: Int?, sex: String?
    ) {
        this.name = name
        this.email = email
        this.image = image
        this.follower = follower
        this.following = following
        this.introduce = introduce
        this.age = age
        this.sex = sex
    }
}