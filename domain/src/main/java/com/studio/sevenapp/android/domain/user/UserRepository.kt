package com.studio.sevenapp.android.domain.user

import com.google.firebase.auth.FirebaseUser
import com.studio.sevenapp.android.domain.model.User

interface UserRepository {
    fun getCurrentUser(): FirebaseUser?
    suspend fun getRankingByGenre(genre: String): List<User>
}