package com.studio.sevenapp.android.domain.user

import com.google.firebase.auth.FirebaseUser
import com.studio.sevenapp.android.domain.model.Genre
import com.studio.sevenapp.android.domain.model.User

class UserUseCaseImpl(
    private val userRepository: UserRepository
) : UserUseCase {

    override fun isUserLogged(): Boolean {
        return userRepository.getCurrentUser() != null
    }

    override fun getCurrentUser(): FirebaseUser? {
        return userRepository.getCurrentUser()
    }

    override suspend fun getRankingByGenre(genre: Genre): List<User> {
        return userRepository.getRankingByGenre(genre = genre.name)
    }
}