package com.studio.sevenapp.android.firebase.firestore

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.studio.sevenapp.android.data.infra.Firestore

class FirestoreImpl(
    private val firestore: FirebaseFirestore
): Firestore {

    override fun updateDocumentField(
        collectionRef: String,
        documentRef: String,
        fieldHashMap: HashMap<String, HashMap<String, Int>>
    ) {
        firestore.collection(collectionRef).document(documentRef).set(fieldHashMap, SetOptions.merge())
    }
}