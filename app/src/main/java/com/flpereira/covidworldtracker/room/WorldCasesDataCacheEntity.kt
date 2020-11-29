package com.flpereira.covidworldtracker.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "WorldCasesData")
data class WorldCasesDataCacheEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "updated")
    val updated: Long,

    @ColumnInfo(name = "cases")
    val cases: Int,

    @ColumnInfo(name = "todayCases")
    val todayCases: Int,

    @ColumnInfo(name = "deaths")
    val deaths: Int,

    @ColumnInfo(name = "todayDeaths")
    val todayDeaths: Int,

    @ColumnInfo(name = "recovered")
    val recovered: Int,

    @ColumnInfo(name = "todayRecovered")
    val todayRecovered: Int,

    @ColumnInfo(name = "active")
    val active: Int,

    @ColumnInfo(name = "critical")
    val critical: Int,

    @ColumnInfo(name = "casesPerOneMillion")
    val casesPerOneMillion: Int,

    @ColumnInfo(name = "deathsPerOneMillion")
    val deathsPerOneMillion: Double,

    @ColumnInfo(name = "tests")
    val tests: Int,

    @ColumnInfo(name = "testsPerOneMillion")
    val testsPerOneMillion: Double,

    @ColumnInfo(name = "population")
    val population: Long,

    @ColumnInfo(name = "oneCasePerPeople")
    val oneCasePerPeople: Int,

    @ColumnInfo(name = "oneDeathPerPeople")
    val oneDeathPerPeople: Int,

    @ColumnInfo(name = "oneTestPerPeople")
    val oneTestPerPeople: Int,

    @ColumnInfo(name = "activePerOneMillion")
    val activePerOneMillion: Double,

    @ColumnInfo(name = "recoveredPerOneMillion")
    val recoveredPerOneMillion: Double,

    @ColumnInfo(name = "criticalPerOneMillion")
    val criticalPerOneMillion: Double,

    @ColumnInfo(name = "affectedCountries")
    val affectedCountries: Int
)