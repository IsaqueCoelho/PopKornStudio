package com.studio.sevenapp.android.firebase.authentication

import com.google.firebase.auth.FirebaseAuth

class FirebaseAuthenticationImpl(
    private val firebaseAuth: FirebaseAuth
) : com.studio.sevenapp.android.data.user.FirebaseAuthentication {

    override fun currentUser() = firebaseAuth.currentUser
}