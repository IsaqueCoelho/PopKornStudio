package com.studio.sevenapp.android.domain.user

import com.google.firebase.auth.FirebaseUser

interface UserUseCase {
    fun isUserLogged() : Boolean
    fun getCurrentUser(): FirebaseUser?
}