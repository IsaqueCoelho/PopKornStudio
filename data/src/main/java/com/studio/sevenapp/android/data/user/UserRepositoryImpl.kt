package com.studio.sevenapp.android.data.user

import com.studio.sevenapp.android.domain.user.UserRepository

class UserRepositoryImpl(
    private val firebaseAuthentication: FirebaseAuthentication
): UserRepository {
    override fun getCurrentUser() = firebaseAuthentication.currentUser()
}