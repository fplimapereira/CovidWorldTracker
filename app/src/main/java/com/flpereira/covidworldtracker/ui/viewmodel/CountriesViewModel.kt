package com.flpereira.covidworldtracker.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flpereira.covidworldtracker.model.CountryListItem
import com.flpereira.covidworldtracker.repository.Repository
import com.flpereira.covidworldtracker.util.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class CountriesViewModel @ViewModelInject constructor(private val repo: Repository) : ViewModel() {

    private val _countriesListData: MutableLiveData<DataState<List<CountryListItem>>> =
        MutableLiveData()
    val countriesListData: LiveData<DataState<List<CountryListItem>>>
        get() = _countriesListData

    val filteredCountriesListData = MutableLiveData<List<CountryListItem>>()


    init {
        viewModelScope.launch {
            repo.getCountriesData()
                .onEach {
                    _countriesListData.value = it
                }.launchIn(viewModelScope)
        }

    }


    fun filterList(char: String) {
        if (countriesListData.value is DataState.Success) {
            filteredCountriesListData.value =
                (countriesListData.value as DataState.Success<List<CountryListItem>>).data.filter {
                    it.name.contains(char, ignoreCase = true)
                }
        }
    }
}
