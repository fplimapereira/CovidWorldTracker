package com.flpereira.covidworldtracker.model

data class WorldCasesData(
val id: Int,
val updated: Long,
val cases: Int,
val todayCases: Int,
val deaths: Int,
val todayDeaths: Int,
val recovered: Int,
val todayRecovered: Int,
val active: Int,
val critical: Int,
val casesPerOneMillion: Int,
val deathsPerOneMillion: Double,
val tests: Int,
val testsPerOneMillion: Double,
val population: Long,
val oneCasePerPeople: Int,
val oneDeathPerPeople: Int,
val oneTestPerPeople: Int,
val activePerOneMillion: Double,
val recoveredPerOneMillion: Double,
val criticalPerOneMillion: Double,
val affectedCountries: Int
)