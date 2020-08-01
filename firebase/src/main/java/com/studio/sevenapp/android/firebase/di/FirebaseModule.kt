package com.studio.sevenapp.android.firebase.di

import com.studio.sevenapp.android.firebase.authentication.insertAuthentication
import com.studio.sevenapp.android.firebase.firestore.insertFirestore
import com.studio.sevenapp.android.firebase.remoteconfig.insertRemoteConfig
import org.koin.dsl.module

val firebaseModule = module {

    // Authentication
    insertAuthentication()

    // Firestore
    insertFirestore()

    // Remote Config
    insertRemoteConfig()
}