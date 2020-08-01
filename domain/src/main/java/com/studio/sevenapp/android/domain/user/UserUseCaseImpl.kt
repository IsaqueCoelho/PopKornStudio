package com.studio.sevenapp.android.domain.user

import com.google.firebase.auth.FirebaseUser
import com.studio.sevenapp.android.domain.base.RemoteConfigRepository
import com.studio.sevenapp.android.domain.model.Genre
import com.studio.sevenapp.android.domain.model.User

class UserUseCaseImpl(
    private val userRepository: UserRepository,
    private val RemoteConfigRepository: RemoteConfigRepository
) : UserUseCase {

    override suspend fun isUserLogged(): Boolean {
        RemoteConfigRepository.refreshRemoteConfigData()
        return userRepository.getCurrentUser() != null
    }

    override fun getCurrentUser(): FirebaseUser? {
        return userRepository.getCurrentUser()
    }

    override suspend fun getRankingByGenre(genre: Genre): List<User> {
        return userRepository.getRankingByGenre(genre = genre.name)
    }
}