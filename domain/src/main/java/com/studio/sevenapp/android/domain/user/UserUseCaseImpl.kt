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

    override suspend fun getCurrentRanking(genre: Genre): List<User> {
        val userList = listOf(
            User(
                name = "Test1",
                pictureUrl = "https://lh3.googleusercontent.com/ogw/ADGmqu9ay20pUmjnXzLiLvZGfzGFZST5naDaZIO17UBF=s83-c-mo",
                level = 7
            ),
            User(
                name = "Test1",
                pictureUrl = "https://static3.tcdn.com.br/img/img_prod/460977/guarda_chuva_katana_samurai_42776_1_20190411193854.jpg",
                level = 3
            ),
            User(
                name = "Test1",
                pictureUrl = "https://2.bp.blogspot.com/-pkq1t0htRqc/VvMZ0rhAc8I/AAAAAAAAy6U/ebmTX_8hHHQC18KiZ5HS5rbD8SPV0pdFA/s1600/que%2Bdeselegante.jpg",
                level = 1
            )
        )

        return userList
    }
}