package com.studio.sevenapp.android.popkornstudio.splash

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.studio.sevenapp.android.popkornstudio.R
import com.studio.sevenapp.android.popkornstudio.base.BaseActivity
import com.studio.sevenapp.android.popkornstudio.features.home.HomeActivity
import com.studio.sevenapp.android.popkornstudio.signin.SignInActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : BaseActivity<SplashViewModel>() {

    override val viewModel by viewModel<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        prepareObservers()
    }

    private fun prepareObservers() {
        viewModel.shouldChangeScreen()
            .observe(this, Observer { result ->
                changeScreen(
                    getIntentByAuthorization(result),
                    false
                )
            })
    }

    private fun getIntentByAuthorization(userAuthenticated: Boolean): Intent {
        return when (userAuthenticated) {
            true -> {
                Intent(this, HomeActivity::class.java)
            }
            else -> {
                Intent(this, SignInActivity::class.java)
            }
        }
    }
}
