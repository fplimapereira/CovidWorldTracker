package com.flpereira.covidworldtracker.retrofit

import retrofit2.http.GET
import retrofit2.http.Query

interface TrackerRetrofit {

    @GET("all")
    suspend fun getWorldCasesData(): WorldCasesNetworkEntity

    @GET("countries")
    suspend fun getCountriesData(): List<CountriesDataNetworkEntity>

    @GET("countries")
    suspend fun getCountryData(
        @Query("q")
        searchQuery: String
    ): CountriesDataNetworkEntity
}