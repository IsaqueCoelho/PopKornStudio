package com.studio.sevenapp.android.popkornstudio.signin

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module

fun Module.insertSignIn(){
    viewModel {
        SignInViewModel()
    }
}