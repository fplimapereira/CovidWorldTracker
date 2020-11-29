package com.flpereira.covidworldtracker.retrofit

import com.flpereira.covidworldtracker.BuildConfig
import com.flpereira.covidworldtracker.model.WorldCasesData
import com.flpereira.covidworldtracker.util.EntityMapper
import javax.inject.Inject

class WorldDataNetworkMapper
@Inject
constructor(): EntityMapper<WorldCasesNetworkEntity, WorldCasesData>{
    override fun mapFromEntity(entity: WorldCasesNetworkEntity): WorldCasesData {
        return WorldCasesData(
            id = BuildConfig.DB_ID,
            updated = entity.updated,
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
            oneCasePerPeople = entity.oneCasePerPeople,
            oneDeathPerPeople = entity.oneDeathPerPeople,
            oneTestPerPeople = entity.oneTestPerPeople,
            activePerOneMillion = entity.activePerOneMillion,
            recoveredPerOneMillion = entity.recoveredPerOneMillion,
            criticalPerOneMillion = entity.criticalPerOneMillion,
            affectedCountries = entity.affectedCountries
        )
    }

    override fun mapToEntity(domainModel: WorldCasesData): WorldCasesNetworkEntity {
        return WorldCasesNetworkEntity(
            updated = domainModel.updated,
            cases = domainModel.cases,
            todayCases = domainModel.todayCases,
            deaths = domainModel.deaths,
            todayDeaths = domainModel.todayDeaths,
            recovered = domainModel.recovered,
            todayRecovered = domainModel.todayRecovered,
            active = domainModel.active,
            critical = domainModel.critical,
            casesPerOneMillion = domainModel.casesPerOneMillion,
            deathsPerOneMillion = domainModel.deathsPerOneMillion,
            tests = domainModel.tests,
            testsPerOneMillion = domainModel.testsPerOneMillion,
            population = domainModel.population,
            oneCasePerPeople = domainModel.oneCasePerPeople,
            oneDeathPerPeople = domainModel.oneDeathPerPeople,
            oneTestPerPeople = domainModel.oneTestPerPeople,
            activePerOneMillion = domainModel.activePerOneMillion,
            recoveredPerOneMillion = domainModel.recoveredPerOneMillion,
            criticalPerOneMillion = domainModel.criticalPerOneMillion,
            affectedCountries = domainModel.affectedCountries
        )
    }
}