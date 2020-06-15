package com.studio.sevenapp.android.data.user

import com.google.firebase.auth.FirebaseUser

interface FirebaseAuthentication {
    fun currentUser() : FirebaseUser?
}