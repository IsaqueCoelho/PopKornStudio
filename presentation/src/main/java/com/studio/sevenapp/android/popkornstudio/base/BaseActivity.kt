package com.studio.sevenapp.android.popkornstudio.base

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.studio.sevenapp.android.popkornstudio.R
import com.studio.sevenapp.android.popkornstudio.extensions.setBackground
import com.studio.sevenapp.android.popkornstudio.extensions.setElevation
import com.studio.sevenapp.android.popkornstudio.extensions.setMargin

abstract class BaseActivity<ViewModel : BaseViewModel> : AppCompatActivity(){

    abstract val viewModel: ViewModel

    private val snackBarNoInternetConnection by lazy {
        Snackbar
            .make(
                window.decorView.findViewById(android.R.id.content),
                getString(R.string.base_no_internet_connection_error),
                Snackbar.LENGTH_INDEFINITE
            )
            .apply {
                setAction(getString(R.string.base_no_internet_connection_close)) {}
                setActionTextColor(ContextCompat.getColor(context, android.R.color.white))
                setMargin(
                    left = 16,
                    top = 16,
                    right = 16,
                    bottom = 32
                )
                setBackground(R.color.DEFAULT_RED)
                setElevation(6f)
            }
    }

    protected fun changeScreen(intent: Intent, addToStack: Boolean){
        startActivity(intent)

        if(!addToStack){
            finish()
        }
    }

}