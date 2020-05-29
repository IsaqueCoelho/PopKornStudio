package com.studio.sevenapp.android.data.user

import com.studio.sevenapp.android.domain.user.UserRepository
import com.studio.sevenapp.android.firebase.authentication.FirebaseAuthenticationImpl

class UserRepositoryImpl(
    private val firebaseAuthentication: FirebaseAuthenticationImpl
): UserRepository {

    override fun getCurrentUser() = firebaseAuthentication.currentUser()
}