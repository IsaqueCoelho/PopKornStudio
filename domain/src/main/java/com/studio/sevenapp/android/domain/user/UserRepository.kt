package com.studio.sevenapp.android.domain.user

import com.google.firebase.auth.FirebaseUser

interface UserRepository {
    fun getCurrentUser(): FirebaseUser?
}