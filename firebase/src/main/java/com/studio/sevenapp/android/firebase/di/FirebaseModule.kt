package com.studio.sevenapp.android.firebase.di

import com.studio.sevenapp.android.firebase.authentication.insertAuthentication
import com.studio.sevenapp.android.firebase.firestore.insertFirestore
import org.koin.dsl.module

val firebaseModule = module {

    // Authentication
    insertAuthentication()

    // Firestore
    insertFirestore()
}