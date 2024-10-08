package com.dicoding.androidexpertsubmission.core.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "games")
data class GamesEntity(

    @ColumnInfo(name = "rating")
    val rating: Double,

    @ColumnInfo(name = "backgroundImage")
    val backgroundImage: String,

    @ColumnInfo(name = "name")
    val name: String,

    @PrimaryKey
    val id: Int,

    @ColumnInfo(name = "released")
    val released: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
): Parcelable