package com.studio.sevenapp.android.popkornstudio.splash

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.studio.sevenapp.android.popkornstudio.R
import com.studio.sevenapp.android.popkornstudio.base.BaseActivity
import com.studio.sevenapp.android.popkornstudio.login.LoginActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : BaseActivity<SplashViewModel>() {

    override val viewModel by viewModel<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        setObservers()
    }

    private fun setObservers() {

        viewModel.shouldChangeScreen()
            .observe(this, Observer { result ->
                if (result) {
                    changeScreen(
                        Intent(
                            this,
                            LoginActivity::class.java
                        ),
                        false
                    )
                }
            })
    }
}
