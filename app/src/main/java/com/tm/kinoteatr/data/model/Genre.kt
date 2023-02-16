package com.tm.kinoteatr.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Genre(
    val genre: String
): Parcelable