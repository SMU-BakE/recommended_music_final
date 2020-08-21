package com.example.music_recomend_profile.database

import com.example.music_recomend_profile.R


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

    fun createRecordItem(): ArrayList<RecordItem> {
        val recordItemList: ArrayList<RecordItem> = ArrayList()

        //아래는 예시
        //데이터베이스에서 RecordItem 받아와서 날짜순으로 정렬해야함.
        recordItemList.add(
            RecordItem(
                "설레",
                1588331341000,createSong1()
            )
        )
        recordItemList.add(
            RecordItem(
                "즐거워",
                1587380941000,createSong2()
            )
        )
        recordItemList.add(
            RecordItem(
                "슬퍼",
                1586084941000,createSong3()
            )
        )
        recordItemList.add(
            RecordItem(
                "그냥그래",
                1583406541000
            )
        )
        recordItemList.add(
            RecordItem(
                "기뻐",
                1580555341000
            )
        )
        recordItemList.add(
            RecordItem(
                "화나",
                1580382541000
            )
        )
        recordItemList.add(
            RecordItem(
                "화나",
                1577876941000
            )
        )


        return recordItemList

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


    private fun createSong1(): ArrayList<Song> {
        val songList: ArrayList<Song> = ArrayList()

        songList.add(
            Song("그대와 나 설레임", "어쿠스틱콜라보", "2MvjjJXSX-g", true)
        )

        songList.add(
            Song("나만 봄", "볼빨간 사춘기", "AsXxuIdpkWM", true)
        )

        songList.add(
            Song("벚꽃엔딩", "버스커버스커", "tXV7dfvSefo", true)
        )
        songList.add(
            Song("그대와 나 설레임", "어쿠스틱콜라보", "2MvjjJXSX-g", true)
        )

        songList.add(
            Song("나만 봄", "볼빨간 사춘기", "AsXxuIdpkWM", true)
        )

        songList.add(
            Song("벚꽃엔딩", "버스커버스커", "tXV7dfvSefo", true)
        )
        return songList
    }

    private fun createSong2(): ArrayList<Song> {
        val songList: ArrayList<Song> = ArrayList()

        songList.add(
            Song("출발", "김동률", "xgvckGs6xhU", false)
        )

        songList.add(
            Song("해변의 여인", "쿨", "M2RSYAGdybA", true)
        )

        songList.add(
            Song("여행을 떠나요", "이승기", "xjjHdLFsUtk", true)
        )
        return songList
    }

    private fun createSong3(): ArrayList<Song> {
        val songList: ArrayList<Song> = ArrayList()

        songList.add(
            Song("헤어지던 밤", "어쿠르브", "http://ddddd", true)
        )

        songList.add(
            Song("고백", "정준일", "http://ddddd", true)
        )

        songList.add(
            Song("헤어져줘서 고마워", "벤", "http://ddddd", true)
        )
        return songList
    }
}

