package com.studio.sevenapp.android.popkornstudio.base

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.google.android.material.snackbar.Snackbar
import com.studio.sevenapp.android.popkornstudio.R
import com.studio.sevenapp.android.popkornstudio.extensions.setBackground
import com.studio.sevenapp.android.popkornstudio.extensions.setElevation
import com.studio.sevenapp.android.popkornstudio.extensions.setMargin
import org.koin.android.ext.android.inject

abstract class BaseActivity<ViewModel : BaseViewModel> : AppCompatActivity() {

    abstract val viewModel: ViewModel

    open val isViewModelOwner = true

    protected var loadStateView: View? = null

    private val connectivityReceiver: ConnectivityReceiver by inject()

    private val customDialog by lazy {
        MaterialDialog(this).noAutoDismiss()
    }

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

    @CallSuper
    override fun onResume() {
        super.onResume()

        if (isViewModelOwner)
            viewModel.onViewResumed()

        registerConnectivityReceiver()
        prepareBaseObservers()
    }

    override fun onPause() {
        super.onPause()
        unregisterConnectivityReceiver()
    }

    private fun unregisterConnectivityReceiver() {
        connectivityReceiver.unregisterNetworkCallback(this)
    }

    private fun registerConnectivityReceiver() {
        connectivityReceiver.registerNetworkCallback(this)
    }

    protected fun changeScreen(intent: Intent, addToStack: Boolean) {
        startActivity(intent)

        if (!addToStack) {
            finish()
        }
    }

    protected fun setToolbarBackButton() {
        supportActionBar?.let { it ->
            it.setDisplayShowHomeEnabled(true)
            it.setDisplayHomeAsUpEnabled(true)
        }
    }

    protected fun setToolbarTitle(title: String) {
        supportActionBar?.title = title
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    protected fun setloadingState(showLoading: Boolean) {
        when {
            showLoading -> showLoadingState()
            !showLoading -> dismissLoadingState()
        }
    }

    protected fun setCustomDialogLayout(customLayout: Int, cancelOnTouchOutside: Boolean = true) {
        customDialog.customView(customLayout)
        customDialog.cancelOnTouchOutside(cancelOnTouchOutside)
    }

    protected fun getCustomDialogViewById(viewId: Int): View {
        return customDialog.findViewById(viewId)
    }

    protected fun customDialogVisibility(state: Boolean) {
        when {
            state && !customDialog.isShowing -> customDialog.show()
            !state && customDialog.isShowing -> customDialog.dismiss()
        }
    }

    private fun prepareBaseObservers() {
        viewModel.showLoading().observe(this, Observer { loadingState ->
            setloadingState(loadingState)
        })

        viewModel.showToast().observe(this, Observer { mustShowToast ->
            when {
                mustShowToast.first -> showToast(getString(mustShowToast.second))
            }
        })

        viewModel.showNoInternetConnection().observe(this, Observer { mustShow ->
            setNoInternetConnection(mustShow = mustShow)
        })
    }

    private fun showLoadingState() {
        loadStateView?.visibility = View.VISIBLE
    }

    private fun dismissLoadingState() {
        loadStateView?.visibility = View.GONE
    }

    private fun setNoInternetConnection(mustShow: Boolean) {
        snackBarNoInternetConnection.apply {
            when {
                mustShow && !isShown -> show()
                !mustShow && isShown -> dismiss()
            }
        }
    }
}