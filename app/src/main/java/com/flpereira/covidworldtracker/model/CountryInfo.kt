package com.flpereira.covidworldtracker.model

data class CountryInfo(
    val id: Int,
    val iso2: String,
    val iso3: String,
    val lat: Float,
    val long: Float,
    val flag: String
)