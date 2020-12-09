package com.flpereira.covidworldtracker.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.flpereira.covidworldtracker.model.WorldCasesData
import com.flpereira.covidworldtracker.repository.Repository
import com.flpereira.covidworldtracker.util.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect

import kotlinx.coroutines.launch

//todo: por padrão geralmente uma view tem um view model pra ela (caso necessário),
// esse viewModel tá sendo usado por mais de uma view o que não é recomendado
@ExperimentalCoroutinesApi
class WorldCasesViewModel @ViewModelInject constructor(private val repo: Repository) : ViewModel() {

    private val _dataState: MutableLiveData<DataState<WorldCasesData>> = MutableLiveData()
    val dataState: LiveData<DataState<WorldCasesData>>
        get() = _dataState


    init {
        viewModelScope.launch {
            repo.getWorldCasesData().collect {
                _dataState.value = it
            }
        }
    }
}