package com.studio.sevenapp.android.data.infra

/**
 * Prints the stack trace of a throwable only if [isLoggingEnabled] is true.
 */
fun Throwable.printStackTrace(isLoggingEnabled: Boolean): Throwable {
    if (isLoggingEnabled)
        printStackTrace()
    return this
}