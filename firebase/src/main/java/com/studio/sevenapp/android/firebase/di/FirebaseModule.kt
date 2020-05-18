package com.studio.sevenapp.android.firebase.di

import com.google.firebase.auth.FirebaseAuth
import com.studio.sevenapp.android.firebase.authentication.FirebaseAuthentication
import com.studio.sevenapp.android.firebase.authentication.FirebaseAuthenticationImpl
import com.studio.sevenapp.android.firebase.authentication.insertAuthentication
import org.koin.dsl.module

val firebaseModule = module {

    // Authentication
    //insertAuthentication()


    single {
        FirebaseAuth.getInstance()
    }

    single<FirebaseAuthentication> {
        FirebaseAuthenticationImpl(get())
    }
}