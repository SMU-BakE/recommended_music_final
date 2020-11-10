package com.bake.recommended_music_final.database


//임시 데이터 생성
class DataExample {
    companion object {
        //생일(날짜) 는 타임스탬프 사용. 날짜 변환 함수는 TimeUtils 참고
        var myProfile: UserProfile = UserProfile(
            "해써니사이드오브", "gildogi@naver.com",
            243,
            150,
            "배고파 치킨사줘\n#노래가좋아\n더이상 쓸말이 생각나지 않는다.", 24, "여성", 830919601000, null
        )

        var songs: List<Song>? = null
        var myCondtion: Condition = Condition()
    }

    fun updateMyProfile(profile: UserProfile) {
        myProfile = profile
    }

    fun getUser(): UserProfile {
        val profile = UserProfile(
            myProfile.name,
            myProfile.email,
            myProfile.follower,
            myProfile.following,
            myProfile.introduce,
            myProfile.age,
            myProfile.sex,
            myProfile.birthday,
            myProfile.imageSrc

        )
        return profile
    }




    //지금은 예시로 팔로우, 팔로잉, 친구찾기에 다 이 데이터 넣어둠. -> 바꿔야함
    fun createFriends(): ArrayList<UserProfile> {
        val userList: ArrayList<UserProfile> = ArrayList()

        userList.add(
            UserProfile("지니", "astic@gmail.com", 100, 105, "요술램프 지니", 21, "남성", 0, null)
        )

        userList.add(
            UserProfile("오구", "ogu@gmail.com", 999, 50, "오리고기 먹지마", 10, "null", 0, null)
        )

        userList.add(
            UserProfile("뚜지", "duu@gmail.com", 999, 500, "두더지더지더지는땅을파지", 7, "null", 0, null)
        )
        userList.add(
            UserProfile("세연세연세세", "se@gmail.com", 302, 105, "요술램프 지니", 21, "남성", 0, null)
        )

        userList.add(
            UserProfile(
                "은데데데데데데ㅔ데데데데이터베이스",
                "dedede@gmail.com",
                63,
                50,
                "오리고기 먹지마",
                10,
                "null",
                0,
                null
            )
        )

        userList.add(
            UserProfile("성민", "smsm@gmail.com", 204, 500, "두더지더지더지는땅을파지", 7, "null", 0, null)
        )

        userList.add(
            UserProfile("주원", "numberone@gmail.com", 999, 990, "두더지더지더지는땅을파지", 7, "null", 0, null)
        )

        userList.add(
            UserProfile("Jinyjiny", "astic@gmail.com", 100, 105, "요술램프 지니", 21, "남성", 0, null)
        )

        userList.add(
            UserProfile("tomandtoms", "tomtom@gmail.com", 999, 50, "오리고기 먹지마", 10, "null", 0, null)
        )
        return userList
    }


}

