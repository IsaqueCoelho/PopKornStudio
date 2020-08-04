package com.studio.sevenapp.android.popkornstudio.base

import android.os.Build

object BuildInfoHelper {
    val androidApiLevel: Int
        get() = Build.VERSION.SDK_INT
}