package com.flpereira.covidworldtracker.retrofit

import com.google.gson.annotations.SerializedName


data class CountriesDataNetworkEntity(
    @SerializedName("updated")
    val updated: Long,
    @SerializedName("country")
    val country: String,
    @SerializedName("countryInfo")
    val countryInfo: CountryInfoNetworkEntity,
    @SerializedName("cases")
    val cases: Int,
    @SerializedName("todayCases")
    val todayCases: Int,
    @SerializedName("deaths")
    val deaths: Int,
    @SerializedName("todayDeaths")
    val todayDeaths: Int,
    @SerializedName("recovered")
    val recovered: Int,
    @SerializedName("todayRecovered")
    val todayRecovered: Int,
    @SerializedName("active")
    val active: Int,
    @SerializedName("critical")
    val critical: Int,
    @SerializedName("casesPerOneMillion")
    val casesPerOneMillion: Float,
    @SerializedName("deathsPerOneMillion")
    val deathsPerOneMillion: Float,
    @SerializedName("tests")
    val tests: Int,
    @SerializedName("testsPerOneMillion")
    val testsPerOneMillion: Float,
    @SerializedName("population")
    val population: Int,
    @SerializedName("continent")
    val continent: String,
    @SerializedName("oneCasePerPeople")
    val oneCasePerPeople: Int,
    @SerializedName("oneDeathPerPeople")
    val oneDeathPerPeople: Int,
    @SerializedName("oneTestPerPeople")
    val oneTestPerPeople: Int,
    @SerializedName("activePerOneMillion")
    val activePerOneMillion: Double,
    @SerializedName("recoveredPerOneMillion")
    val recoveredPerOneMillion: Double,
    @SerializedName("criticalPerOneMillion")
    val criticalPerOneMillion: Double
)