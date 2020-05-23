package com.studio.sevenapp.android.data.userrepositoy

import com.studio.sevenapp.android.domain.userusecase.UserRepository
import com.studio.sevenapp.android.firebase.authentication.FirebaseAuthenticationImpl

class UserRepositoryImpl(
    private val firebaseAuthentication: FirebaseAuthenticationImpl
): UserRepository {

    override fun getCurrentUser() = firebaseAuthentication.currentUser()
}