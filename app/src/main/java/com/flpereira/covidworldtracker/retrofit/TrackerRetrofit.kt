package com.flpereira.covidworldtracker.retrofit

import retrofit2.Response
import retrofit2.http.GET

interface TrackerRetrofit {

    @GET("all")
    suspend fun getWorldCasesData(): WorldCasesNetworkEntity

    @GET("countries")
    suspend fun getCountriesData(): List<CountriesDataNetworkEntity>
}