package com.flpereira.covidworldtracker.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.flpereira.covidworldtracker.retrofit.WorldCasesNetworkEntity

@Dao
interface WorldCasesDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(worldCasesDataEntity: WorldCasesDataCacheEntity): Long

    @Query("SELECT * FROM worldcasesdata")
    suspend fun getWorldCases(): WorldCasesDataCacheEntity
}