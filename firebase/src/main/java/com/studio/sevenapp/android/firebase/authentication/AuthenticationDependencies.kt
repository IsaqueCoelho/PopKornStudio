package com.studio.sevenapp.android.firebase.authentication

import com.google.firebase.auth.FirebaseAuth
import org.koin.core.module.Module

fun Module.insertAuthentication(){

    single {
        FirebaseAuth.getInstance()
    }

    single<com.studio.sevenapp.android.data.user.FirebaseAuthentication> {
        FirebaseAuthenticationImpl(get())
    }
}