package com.dicoding.androidexpertsubmission.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Games(
    val rating: Double,
    val backgroundImage: String,
    val name: String,
    val id: Int,
    val released: String,
    val isFavorite: Boolean
): Parcelable