package com.studio.sevenapp.android.domain.base.error

open class ApiException(
    val code: Int,
    msg: String
) : Exception(msg)