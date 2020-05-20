package com.studio.sevenapp.android.domain.userusecase

import com.google.firebase.auth.FirebaseUser

interface UserUseCase {
    fun isUserLogged() : Boolean
    fun getCurrentUser(): FirebaseUser?
}