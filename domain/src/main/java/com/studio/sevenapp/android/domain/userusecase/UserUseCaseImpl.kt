package com.studio.sevenapp.android.domain.userusecase

import com.studio.sevenapp.android.data.userrepositoy.UserRepository

class UserUseCaseImpl(
    private val userRepository: UserRepository
) : UserUseCase {

    override fun isUserLogged(): Boolean {
        return userRepository.getCurrentUser() != null
    }
}