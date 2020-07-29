package com.studio.sevenapp.android.firebase.authentication

import com.google.firebase.auth.FirebaseAuth
import com.studio.sevenapp.android.data.user.FirebaseAuthentication

class FirebaseAuthenticationImpl(
    private val firebaseAuth: FirebaseAuth
) : FirebaseAuthentication {

    override fun currentUser() = firebaseAuth.currentUser
}