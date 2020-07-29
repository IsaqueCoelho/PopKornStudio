package com.studio.sevenapp.android.data.user

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import com.studio.sevenapp.android.data.infra.CollectionsEnum
import com.studio.sevenapp.android.data.infra.Firestore
import com.studio.sevenapp.android.domain.model.User
import com.studio.sevenapp.android.domain.user.UserRepository

class UserRepositoryImpl(
    private val firebaseAuthentication: FirebaseAuthentication,
    private val firestore: Firestore
) : UserRepository {
    override fun getCurrentUser() = firebaseAuthentication.currentUser()

    override suspend fun getRankingByGenre(genre: String): List<User> {

        val collection = firestore.getCollectionWithLimitAndOrder(
            collectionRef = CollectionsEnum.USERS.name,
            orderField = genre,
            orderType = Query.Direction.DESCENDING,
            limit = 100
        )

        return when {
            collection != null -> {
                getRankingFromCollection(documentList = collection.documents, genre = genre)
            }
            else -> emptyList()
        }
    }

    private fun getRankingFromCollection(
        documentList: List<DocumentSnapshot>,
        genre: String
    ): List<User> {
        val userList = mutableListOf<User>()

        documentList.forEach { documentSnapshot ->
            userList.add(
                User(
                    name = documentSnapshot.data!!["name"].toString(),
                    level = documentSnapshot.data!![genre].toString().toInt(),
                    pictureUrl = documentSnapshot.data!!["pictureUrl"].toString()
                )
            )
        }

        return userList
    }
}