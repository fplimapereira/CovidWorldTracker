package com.flpereira.covidworldtracker.model

data class CountriesData(
    val updated: Long,
    val country: String,
    val countryInfo: CountryInfo,
    val cases: Int,
    val todayCases: Int,
    val deaths: Int,
    val todayDeaths: Int,
    val recovered: Int,
    val todayRecovered: Int,
    val active: Int,
    val critical: Int,
    val casesPerOneMillion: Float,
    val deathsPerOneMillion: Float,
    val tests: Int,
    val testsPerOneMillion: Float,
    val population: Int,
    val continent: String,
    val oneCasePerPeople: Int,
    val oneDeathPerPeople: Int,
    val oneTestPerPeople: Int,
    val activePerOneMillion: Double,
    val recoveredPerOneMillion: Double,
    val criticalPerOneMillion: Double
)