package com.flpereira.covidworldtracker.di

import com.flpereira.covidworldtracker.repository.Repository
import com.flpereira.covidworldtracker.retrofit.CountriesListNetworkMapper
import com.flpereira.covidworldtracker.retrofit.CountryNetworkMapper
import com.flpereira.covidworldtracker.retrofit.WorldDataNetworkMapper
import com.flpereira.covidworldtracker.retrofit.TrackerRetrofit
import com.flpereira.covidworldtracker.room.CacheMapper
import com.flpereira.covidworldtracker.room.WorldCasesDataDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(
        worldCasesDataDao: WorldCasesDataDao,
        retrofit: TrackerRetrofit,
        cacheMapper: CacheMapper,
        worldDataNetworkMapper: WorldDataNetworkMapper,
        countriesListNetworkMapper: CountriesListNetworkMapper,
        countryNetworkMapper: CountryNetworkMapper): Repository{
        return Repository(worldCasesDataDao, retrofit, cacheMapper, worldDataNetworkMapper, countriesListNetworkMapper, countryNetworkMapper)
    }
}