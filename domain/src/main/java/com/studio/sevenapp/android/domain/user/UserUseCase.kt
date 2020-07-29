package com.studio.sevenapp.android.domain.user

import com.google.firebase.auth.FirebaseUser
import com.studio.sevenapp.android.domain.model.Genre
import com.studio.sevenapp.android.domain.model.User

interface UserUseCase {
    fun isUserLogged() : Boolean
    fun getCurrentUser(): FirebaseUser?
    suspend fun getRankingByGenre(genre: Genre): List<User>
}