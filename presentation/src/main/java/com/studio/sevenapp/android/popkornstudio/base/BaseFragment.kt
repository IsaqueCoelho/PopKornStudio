package com.studio.sevenapp.android.popkornstudio.base

import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import java.io.Serializable
import java.lang.Boolean.getBoolean
import java.lang.reflect.Array.*

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

    inline fun <reified T> lazyExtra(key: String, default: T): Lazy<T> {
        return lazy { getExtra(key, default) }
    }

    inline fun <reified T> getExtra(key: String, default: T): T {
        val result: Any? = arguments?.run {
            when (default) {
                is Boolean -> getBoolean(key)
                is Byte -> getByte(key)
                is Char -> getChar(key)
                is Short -> getShort(key)
                is Int -> getInt(key)
                is Long -> getLong(key)
                is Float -> getFloat(key)
                is Double -> getDouble(key)
                is String -> getString(key)
                is CharSequence -> getCharSequence(key)
                is Serializable -> getSerializable(key)
                else -> null
            }
        }

        return (result as? T) ?: default
    }
}