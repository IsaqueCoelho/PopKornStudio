package com.studio.sevenapp.android.data.infra

interface Firestore {
    fun updateDocumentField(
        collectionRef: String,
        documentRef: String,
        fieldkey: String,
        fieldValue: String,
        fieldValueType: FieldTypeEnum
    )
}