package com.flpereira.covidworldtracker.retrofit

import com.flpereira.covidworldtracker.model.CountriesData
import com.flpereira.covidworldtracker.model.CountryInfo
import com.flpereira.covidworldtracker.util.EntityMapper
import javax.inject.Inject

class CountryNetworkMapper @Inject constructor(): EntityMapper<CountriesDataNetworkEntity, CountriesData>{
    override fun mapFromEntity(entity: CountriesDataNetworkEntity): CountriesData {
        return CountriesData(
            updated = entity.updated,
            country = entity.country,
            countryInfo = mapToCountryInfo(entity.countryInfo),
            cases = entity.cases,
            todayCases = entity.todayCases,
            deaths = entity.deaths,
            todayDeaths = entity.todayDeaths,
            recovered = entity.recovered,
            todayRecovered = entity.todayRecovered,
            active = entity.active,
            critical = entity.critical,
            casesPerOneMillion = entity.casesPerOneMillion,
            deathsPerOneMillion = entity.deathsPerOneMillion,
            tests = entity.tests,
            testsPerOneMillion = entity.testsPerOneMillion,
            population = entity.population,
            continent = entity.continent,
            oneCasePerPeople = entity.oneCasePerPeople,
            oneDeathPerPeople = entity.oneDeathPerPeople,
            oneTestPerPeople = entity.oneTestPerPeople,
            activePerOneMillion = entity.activePerOneMillion,
            recoveredPerOneMillion = entity.recoveredPerOneMillion,
            criticalPerOneMillion = entity.criticalPerOneMillion
        )
    }

    override fun mapToEntity(domainModel: CountriesData): CountriesDataNetworkEntity {
        TODO("Not yet implemented")
    }

    private fun mapToCountryInfo(entity: CountryInfoNetworkEntity): CountryInfo {
        return CountryInfo(
            id = entity.id,
            iso2 = entity.iso2,
            iso3 = entity.iso3,
            lat = entity.lat,
            long = entity.long,
            flag = entity.flag
        )
    }

}