package com.flpereira.covidworldtracker.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flpereira.covidworldtracker.model.CountriesData
import com.flpereira.covidworldtracker.repository.Repository
import com.flpereira.covidworldtracker.util.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class CountryViewModel @ViewModelInject constructor(private val repo: Repository) : ViewModel() {

    private val _countryData: MutableLiveData<DataState<CountriesData>> = MutableLiveData()
    val countryData: LiveData<DataState<CountriesData>>
        get() = _countryData

        fun getData(country: String){
            viewModelScope.launch {
                repo.getCountryData(country).collect {
                    _countryData.value = it
                }
            }
        }


}
