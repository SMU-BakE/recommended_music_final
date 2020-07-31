package com.example.music_recomend_profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.music_recomend_profile.database.DataExample
import com.example.music_recomend_profile.database.RecordItem


class RecordListFragment : Fragment() {
    private var recyclerView: RecyclerView? = null
    private var recordItem: ArrayList<RecordItem>? = null
    private var gridLayoutManager: GridLayoutManager? = null
    private var recordAdapter: Adapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_record_list, container, false)
        // Inflate the layout for this fragment
        recyclerView = view.findViewById(R.id.recordRV)
        gridLayoutManager =
            GridLayoutManager(context, 3, LinearLayoutManager.VERTICAL, false)
        recyclerView?.layoutManager = gridLayoutManager
        recyclerView?.setHasFixedSize(true)

        recordItem = ArrayList()
        recordItem = setRecordData()
        recordAdapter = Adapter(context!!, recordItem!!)

        recyclerView?.adapter = recordAdapter

        return view
    }

    private fun setRecordData(): ArrayList<RecordItem> {
        //임시 데이터. DB 에서 불러와서 recordItem 에 넣어주세요.
        val recordItem : ArrayList<RecordItem> = DataExample().createRecordItem()
        return recordItem
    }


}
