package com.flpereira.covidworldtracker.repository


import com.flpereira.covidworldtracker.model.CountriesData
import com.flpereira.covidworldtracker.model.CountryListItem
import com.flpereira.covidworldtracker.model.WorldCasesData
import com.flpereira.covidworldtracker.retrofit.CountriesListNetworkMapper
import com.flpereira.covidworldtracker.retrofit.WorldDataNetworkMapper
import com.flpereira.covidworldtracker.retrofit.TrackerRetrofit
import com.flpereira.covidworldtracker.room.CacheMapper
import com.flpereira.covidworldtracker.room.WorldCasesDataDao
import com.flpereira.covidworldtracker.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class Repository(
    private val worldCasesDataDao: WorldCasesDataDao,
    private val retrofit: TrackerRetrofit,
    private val cacheMapper: CacheMapper,
    private val worldDataNetworkMapper: WorldDataNetworkMapper,
    private val countriesListNetworkMapper: CountriesListNetworkMapper
){
    suspend fun getWorldCasesData(): Flow<DataState<WorldCasesData>> =  flow{
        emit(DataState.Loading)
        try {
            val networkData = retrofit.getWorldCasesData()
            val worldCasesData = worldDataNetworkMapper.mapFromEntity(networkData)
            worldCasesDataDao.upsert(cacheMapper.mapToEntity(worldCasesData))
            val cachedData = worldCasesDataDao.getWorldCases()
            emit(DataState.Success(cacheMapper.mapFromEntity(cachedData)))
        }catch (e: Exception){
            emit(DataState.Error(e))
        }
    }

    suspend fun getCountriesData(): Flow<DataState<List<CountryListItem>>> = flow {
        emit(DataState.Loading)
        try {
            val networkCountriesData = retrofit.getCountriesData()
            val countriesList = countriesListNetworkMapper.mapFromEntityList(networkCountriesData)
            emit(DataState.Success(countriesList))
        }catch (e: Exception){
            emit(DataState.Error(e))
        }
    }
}