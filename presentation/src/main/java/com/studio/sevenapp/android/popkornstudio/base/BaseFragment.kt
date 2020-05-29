package com.studio.sevenapp.android.popkornstudio.base

import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment

abstract class BaseFragment<ViewModel : BaseViewModel>(fragmentView: Int) : Fragment(fragmentView){

    abstract val viewModel: ViewModel
    open val isViewModelOwner = true

    @CallSuper
    override fun onResume() {
        super.onResume()

        if (isViewModelOwner)
            viewModel.onViewResumed()
    }

    @CallSuper
    override fun onStop() {
        super.onStop()
        if (isViewModelOwner)
            viewModel.onViewStoped()
    }
}