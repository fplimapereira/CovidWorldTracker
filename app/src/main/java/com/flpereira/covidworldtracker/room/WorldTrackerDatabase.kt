package com.flpereira.covidworldtracker.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.flpereira.covidworldtracker.BuildConfig

@Database(entities = [WorldCasesDataCacheEntity::class], version = 1)
abstract class WorldTrackerDatabase: RoomDatabase() {

    abstract fun worldCasesDao(): WorldCasesDataDao

    companion object{
        val DATABASE_NAME: String = BuildConfig.DB_NAME
    }
}