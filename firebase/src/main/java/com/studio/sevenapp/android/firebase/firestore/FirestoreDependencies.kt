package com.studio.sevenapp.android.firebase.firestore

import com.google.firebase.firestore.FirebaseFirestore
import com.studio.sevenapp.android.data.infra.Firestore
import org.koin.core.module.Module

fun Module.insertFirestore(){
    single {
        FirebaseFirestore.getInstance()
    }

    single<Firestore>{
        FirestoreImpl(get())
    }
}