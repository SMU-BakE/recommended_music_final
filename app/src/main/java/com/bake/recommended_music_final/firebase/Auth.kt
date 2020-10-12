package com.bake.recommended_music_final.firebase


import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Auth {
    private val firebaseAuth = Firebase.auth

    val user: FirebaseUser?
        get() {
            return firebaseAuth.currentUser
        }
//    fun getUser(): FirebaseUser? {
//        return firebaseAuth.currentUser
//    }

    fun doSignOut() {
        Firebase.auth.signOut()
    }
}