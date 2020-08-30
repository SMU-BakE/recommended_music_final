package com.bake.recommended_music_final.friends

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bake.recommended_music_final.R
import com.bake.recommended_music_final.database.DataExample
import com.bake.recommended_music_final.database.UserProfile
import kotlinx.android.synthetic.main.activity_following.*
import org.jetbrains.anko.toast


class Following : AppCompatActivity() {

    private lateinit var listRV: RecyclerView
    private lateinit var searchView: SearchView

    //검색어
    private lateinit var searchWordInput: String

    private val followingSearchList = arrayListOf<UserProfile>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_following)

        initialView()

        backButton.setOnClickListener {
            onBackPressed()
        }
    }

    private fun initialView() {
        listRV = findViewById(R.id.friendSearchRV)

        listRV.apply {
            adapter = FollowingAdapter(

                //예시 데이터. 바꿔주세요~
                //여기서 필요한 데이터 : 내가 팔로잉 한 사람 데이터
                following = DataExample().createFriends(),
                context = this@Following
            )
            layoutManager = LinearLayoutManager(this@Following)
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


    //데이터베이스에서 나의 팔로잉 검색하기 구현하고 listRV랑 연결해주세요오.
    private fun doSearch() {

    }


}