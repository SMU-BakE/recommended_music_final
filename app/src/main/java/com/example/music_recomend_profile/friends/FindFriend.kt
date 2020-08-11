package com.example.music_recomend_profile.friends

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.music_recomend_profile.R
import com.example.music_recomend_profile.database.DataExample
import com.example.music_recomend_profile.database.UserProfile
import kotlinx.android.synthetic.main.activity_find_friend.*
import org.jetbrains.anko.toast



class FindFriend : AppCompatActivity() {

    private lateinit var listRV: RecyclerView
    private lateinit var searchView: SearchView

    //검색어
    private lateinit var searchWordInput: String

    private val friendSearchList = arrayListOf<UserProfile>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_friend)

        initialView()

        backButton.setOnClickListener {
            onBackPressed()
        }
    }

    private fun initialView() {
        listRV = findViewById(R.id.friendSearchRV)

        listRV.apply {
            adapter = FindFriendAdapter(

                //예시 데이터. 바꿔주세요~
                //여기서 필요한 데이터 : 전체 유저 데이터
                findFriend = DataExample().createFriends(),
                context = this@FindFriend
            )
            layoutManager = LinearLayoutManager(this@FindFriend)
        }

        searchView = findViewById(R.id.friendSearchView)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchWordInput = query.toString()

                if (searchWordInput.isEmpty()) {
                    return false
                }
                toast(searchWordInput)
                doSearch()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })


    }


    //데이터베이스에서 전체 친구 검색하기 구현하고 listRV랑 연결해주세요오.
    private fun doSearch() {

    }


}