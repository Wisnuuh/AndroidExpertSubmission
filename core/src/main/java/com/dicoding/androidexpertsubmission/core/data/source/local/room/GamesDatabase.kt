package com.dicoding.androidexpertsubmission.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dicoding.androidexpertsubmission.core.data.source.local.entity.GamesEntity

@Database(entities = [GamesEntity::class], version = 2, exportSchema = false)
abstract class GamesDatabase : RoomDatabase() {

    abstract fun gamesDao(): GamesDao

}