package com.studio.sevenapp.android.firebase.firestore

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.studio.sevenapp.android.data.infra.FieldTypeEnum
import com.studio.sevenapp.android.data.infra.Firestore

class FirestoreImpl(
    private val firestore: FirebaseFirestore
): Firestore {

    override fun updateDocumentField(collectionRef: String, documentRef: String, fieldkey: String, fieldValue: String, fieldValueType: FieldTypeEnum) {
        val data = hashMapOf(fieldkey to getValueByType(fieldValueType, fieldValue))
        firestore.collection(collectionRef).document(documentRef).set(data, SetOptions.merge())
    }

    private fun getValueByType(
        fieldValueType: FieldTypeEnum,
        fieldValue: String
    ): Any {
        return when(fieldValueType){
            FieldTypeEnum.INT -> fieldValue
            else -> false
        }
    }
}