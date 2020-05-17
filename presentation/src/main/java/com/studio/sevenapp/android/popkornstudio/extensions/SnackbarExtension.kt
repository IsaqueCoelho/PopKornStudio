package com.studio.sevenapp.android.popkornstudio.extensions

import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import com.google.android.material.snackbar.Snackbar

fun Snackbar.setBackground(@DrawableRes drawableRes: Int) {
    this.view.background = ContextCompat.getDrawable(context, drawableRes)
}

fun Snackbar.setMargin(
    left: Int = 0,
    top: Int = 0,
    right: Int = 0,
    bottom: Int = 0
) {
    val params = this.view.layoutParams as ViewGroup.MarginLayoutParams
    params.setMargins(
        left,
        top,
        right,
        bottom
    )
    this.view.layoutParams = params

}

fun Snackbar.setElevation(elevation: Float) {
    ViewCompat.setElevation(this.view, elevation)
}