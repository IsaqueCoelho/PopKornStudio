package com.studio.sevenapp.android.popkornstudio.signin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.studio.sevenapp.android.popkornstudio.R
import com.studio.sevenapp.android.popkornstudio.base.BaseActivity
import com.studio.sevenapp.android.popkornstudio.features.home.HomeActivity
import kotlinx.android.synthetic.main.activity_sign_in.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInActivity : BaseActivity<SignInViewModel>() {

    override val viewModel: SignInViewModel by viewModel()

    private val providers = arrayListOf(
        AuthUI.IdpConfig.GoogleBuilder().build()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        setComponentListening()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN_CODE) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {
                changeScreen(
                    Intent(
                        this,
                        HomeActivity::class.java
                    ),
                    false
                )
            } else {
                checkResultFailed(response)
            }
        }
    }

    private fun setComponentListening() {
        button_login_google.setOnClickListener {
            callAuthentication()
        }
    }

    private fun callAuthentication() {
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setLogo(R.drawable.ic_popcorn)
                .build(),
            RC_SIGN_IN_CODE
        )
    }

    private fun checkResultFailed(response: IdpResponse?) {
        // Sign in failed. If response is null the user canceled the
        // sign-in flow using the back button. Otherwise check
        // response.getError().getErrorCode() and handle the error.
        // ...
        response?.let {
            Toast.makeText(this, "Falha no login, tente novamente!", Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        private const val RC_SIGN_IN_CODE = 0
    }
}
