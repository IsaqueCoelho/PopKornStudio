package com.studio.sevenapp.android.domain.userusecase

import com.google.firebase.auth.FirebaseUser

interface UserRepository {
    fun getCurrentUser(): FirebaseUser?
}