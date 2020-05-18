package com.studio.sevenapp.android.data.userrepositoy

import com.google.firebase.auth.FirebaseUser

interface UserRepository {
    fun getCurrentUser(): FirebaseUser?
}