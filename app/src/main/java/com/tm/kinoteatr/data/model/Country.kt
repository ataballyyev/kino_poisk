package com.tm.kinoteatr.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Country(
    val country: String
): Parcelable