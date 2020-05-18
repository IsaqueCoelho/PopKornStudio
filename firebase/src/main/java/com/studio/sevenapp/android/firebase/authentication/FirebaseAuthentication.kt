package com.studio.sevenapp.android.firebase.authentication

import com.google.firebase.auth.FirebaseUser

interface FirebaseAuthentication {
    fun currentUser() : FirebaseUser?
}