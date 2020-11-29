package com.flpereira.covidworldtracker.retrofit

import com.flpereira.covidworldtracker.model.CountryListItem
import com.flpereira.covidworldtracker.util.EntityMapper
import javax.inject.Inject

class CountriesListNetworkMapper
@Inject constructor(): EntityMapper<CountriesDataNetworkEntity, CountryListItem>{
    override fun mapFromEntity(entity: CountriesDataNetworkEntity): CountryListItem {
        return CountryListItem(
            name = entity.country,
            flagUrl = entity.countryInfo.flag
        )
    }

    override fun mapToEntity(domainModel: CountryListItem): CountriesDataNetworkEntity {
        TODO("Not yet implemented")
    }

    fun mapFromEntityList(entities: List<CountriesDataNetworkEntity>): List<CountryListItem>{
        return entities.map { mapFromEntity(it) }
    }

}