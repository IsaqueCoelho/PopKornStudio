package com.studio.sevenapp.android.domain.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
open class News(val type: Int = 0 ) : Parcelable