package com.studio.sevenapp.android.data.infra

import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot

interface Firestore {
    fun updateDocumentField(
        collectionRef: String,
        documentRef: String,
        fieldHashMap: HashMap<String, Int>
    )

    suspend fun getCollectionWithLimitAndOrder(
        collectionRef: String,
        orderField: String,
        orderType: Query.Direction,
        limit: Long
    ): QuerySnapshot?
}