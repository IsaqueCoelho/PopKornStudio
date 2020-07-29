package com.studio.sevenapp.android.firebase.firestore

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.SetOptions
import com.studio.sevenapp.android.data.infra.Firestore
import kotlinx.coroutines.tasks.await

class FirestoreImpl(
    private val firestore: FirebaseFirestore
) : Firestore {

    override fun updateDocumentField(
        collectionRef: String,
        documentRef: String,
        fieldHashMap: HashMap<String, Int>
    ) {
        firestore.collection(collectionRef).document(documentRef)
            .set(fieldHashMap, SetOptions.merge())
    }

    override suspend fun getCollectionWithLimitAndOrder(
        collectionRef: String,
        orderField: String,
        orderType: Query.Direction,
        limit: Long
    ): QuerySnapshot? {

        return  firestore.collection(collectionRef)
            .orderBy(orderField, orderType)
            .limit(limit)
            .get()
            .await()
    }
}