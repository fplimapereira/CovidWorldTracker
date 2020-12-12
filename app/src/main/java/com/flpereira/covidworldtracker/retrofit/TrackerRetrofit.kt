package com.flpereira.covidworldtracker.retrofit

import retrofit2.http.GET
import retrofit2.http.Path


interface TrackerRetrofit {

    @GET("all")
    suspend fun getWorldCasesData(): WorldCasesNetworkEntity

    @GET("countries")
    suspend fun getCountriesData(): List<CountriesDataNetworkEntity>

    @GET("countries/{country}")
    suspend fun getCountryData(
        @Path("country")
        searchQuery: String
    ): CountriesDataNetworkEntity
}