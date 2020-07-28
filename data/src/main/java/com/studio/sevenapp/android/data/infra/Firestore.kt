package com.studio.sevenapp.android.data.infra

interface Firestore {
    fun updateDocumentField(
        collectionRef: String,
        documentRef: String,
        fieldHashMap: HashMap<String, HashMap<String, Int>>
    )
}