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
            "배고파 치킨사줘", 24, "여성", 830919601000, null
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
                191224
            )
        )
        recordItemList.add(
            RecordItem(
                "즐거워",
                191231
            )
        )
        recordItemList.add(
            RecordItem(
                "슬퍼",
                200408
            )
        )
        recordItemList.add(
            RecordItem(
                "그냥그래",
                200701
            )
        )
        recordItemList.add(
            RecordItem(
                "기뻐",
                200705
            )
        )
        recordItemList.add(
            RecordItem(
                "화나",
                2007010
            )
        )
        recordItemList.add(
            RecordItem(
                "화나",
                2007015
            )
        )

        return recordItemList

    }

    fun createFriends() : ArrayList<UserProfile>{
        val userList: ArrayList<UserProfile> = ArrayList()

        userList.add(
            UserProfile("지니","astic@gmail.com",100,105,"요술램프 지니",21,"남성",0,null)
        )

        userList.add(
            UserProfile("오구","ogu@gmail.com",999,50,"오리고기 먹지마",10,"null",0,null)
        )

        userList.add(
            UserProfile("뚜지","duu@gmail.com",999,500,"두더지더지더지는땅을파지",7,"null",0,null)
        )
        return userList
    }
}