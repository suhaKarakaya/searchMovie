package com.suhakarakaya.searchmovie.Model

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class Ratings(
    var Source: String?,
    var Value: String?
): Serializable