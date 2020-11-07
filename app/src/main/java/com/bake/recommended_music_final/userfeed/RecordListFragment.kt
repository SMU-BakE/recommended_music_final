package com.bake.recommended_music_final.userfeed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bake.recommended_music_final.R
import com.bake.recommended_music_final.database.RecordItem
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase


class RecordListFragment : Fragment() {
    private var recyclerView: RecyclerView? = null

    //    private var recordItem: ArrayList<RecordItem>? = null
    private var recordItems = arrayListOf<RecordItem>()
    private var gridLayoutManager: GridLayoutManager? = null
    private var recordAdapter: Adapter? = null

    private lateinit var db: FirebaseFirestore
    val uid = Firebase.auth.currentUser?.uid

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

        recordAdapter =
            Adapter(
                context!!,
                recordItems
            )

        recyclerView?.adapter = recordAdapter

        updateRecordList()

        return view
    }

    private fun updateRecordList() {
        //임시 데이터. DB 에서 불러와서 recordItem 에 넣어주세요.
        //val recordItem: ArrayList<RecordItem> = DataExample().createRecordItem()

        db = FirebaseFirestore.getInstance()
        db.collection("record_item").whereEqualTo("userId", uid).limit(10)
            .get().addOnCompleteListener {
                val querySnapshot = it.result

                recordItems.clear()
                querySnapshot?.documents?.map { doc ->
                    val item = doc.toObject<RecordItem>()
                    if (item != null) {
                        recordItems.add(item)
                    }
                }

                recordAdapter!!.notifyDataSetChanged()
            }
    }


}
