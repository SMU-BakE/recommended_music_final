package com.bake.recommended_music_final.firebase

import com.bake.recommended_music_final.database.UserProfile
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks.await
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.coroutineScope

class Profile {
    private val db = Firebase.firestore

    private fun profileRef(uid: String): DocumentReference {
        return db.collection("profiles").document(uid)
    }

    fun getProfile(uid: String): Task<UserProfile?> {
        val profile = (profileRef(uid).get().continueWith { snapshotTask ->
            if (!snapshotTask.isSuccessful) {
                throw error("cannot get profile :" + snapshotTask.exception.toString())
            }
            return@continueWith snapshotTask.result?.toObject<UserProfile>()
        })

        return profile
    }


}