package com.studio.sevenapp.android.popkornstudio.di

import com.studio.sevenapp.android.popkornstudio.features.game.category.insertGameCategoryDependencies
import com.studio.sevenapp.android.popkornstudio.features.game.challenge.insertChallengeDependencies
import com.studio.sevenapp.android.popkornstudio.features.game.result.insertChallengeResult
import com.studio.sevenapp.android.popkornstudio.features.home.insertHomeDependencies
import com.studio.sevenapp.android.popkornstudio.signin.insertSignIn
import com.studio.sevenapp.android.popkornstudio.splash.includeSplashScreen
import org.koin.dsl.module

val presentationModule = module {

    // Splash
    includeSplashScreen()

    // SignIn
    insertSignIn()

    // Home
    insertHomeDependencies()

    // Game Category
    insertGameCategoryDependencies()

    // ChallengeQuestion
    insertChallengeDependencies()

    // Challenge Result
    insertChallengeResult()
}