package com.studio.sevenapp.android.firebase.authentication

import com.google.firebase.auth.FirebaseAuth
import com.studio.sevenapp.android.data.user.FirebaseAuthentication
import org.koin.core.module.Module

fun Module.insertAuthentication(){

    single {
        FirebaseAuth.getInstance()
    }

    single<FirebaseAuthentication> {
        FirebaseAuthenticationImpl(get())
    }
}