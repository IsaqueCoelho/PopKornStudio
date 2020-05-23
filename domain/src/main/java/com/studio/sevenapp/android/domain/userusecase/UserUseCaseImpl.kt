package com.studio.sevenapp.android.domain.userusecase

import com.google.firebase.auth.FirebaseUser

class UserUseCaseImpl(
    private val userRepository: UserRepository
) : UserUseCase {

    override fun isUserLogged(): Boolean {
        return userRepository.getCurrentUser() != null
    }

    override fun getCurrentUser(): FirebaseUser? {
        return userRepository.getCurrentUser()
    }
}