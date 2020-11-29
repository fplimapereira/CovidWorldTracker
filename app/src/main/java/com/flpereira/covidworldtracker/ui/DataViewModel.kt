package com.flpereira.covidworldtracker.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.flpereira.covidworldtracker.model.CountryListItem
import com.flpereira.covidworldtracker.model.WorldCasesData
import com.flpereira.covidworldtracker.repository.Repository
import com.flpereira.covidworldtracker.util.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

//todo: por padrão geralmente uma view tem um view model pra ela (caso necessário),
// esse viewModel tá sendo usado por mais de uma view o que não é recomendado
@ExperimentalCoroutinesApi
class DataViewModel @ViewModelInject constructor(private val repo: Repository) : ViewModel() {

    private val _dataState: MutableLiveData<DataState<WorldCasesData>> = MutableLiveData()
    val dataState: LiveData<DataState<WorldCasesData>>
        get() = _dataState

    private val _countriesListData: MutableLiveData<DataState<List<CountryListItem>>> =
        MutableLiveData()
    val countriesListData: LiveData<DataState<List<CountryListItem>>>
        get() = _countriesListData

    val filteredCountriesListData = MutableLiveData<List<CountryListItem>>()

    fun setStateEvent(mainStateEvent: MainStateEvent) {
        viewModelScope.launch {
            when (mainStateEvent) {

                is MainStateEvent.GetWorldDataEvent -> {
                    repo.getWorldCasesData().collect {
                        _dataState.value = it
                    }
                }

                is MainStateEvent.GetCountriesListEvent -> {
                    repo.getCountriesData()
                        .onEach {
                            _countriesListData.value = it
                        }.launchIn(viewModelScope)
                }

                is MainStateEvent.GetCountryDataEvent -> {
                    //TODO
                }
            }
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

sealed class MainStateEvent {

    object GetWorldDataEvent : MainStateEvent()

    object GetCountriesListEvent : MainStateEvent()

    object GetCountryDataEvent : MainStateEvent()
}