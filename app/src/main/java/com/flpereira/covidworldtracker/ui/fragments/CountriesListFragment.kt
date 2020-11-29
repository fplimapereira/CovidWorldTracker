package com.flpereira.covidworldtracker.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.flpereira.covidworldtracker.BuildConfig
import com.flpereira.covidworldtracker.R
import com.flpereira.covidworldtracker.adapter.CountriesListAdapter
import com.flpereira.covidworldtracker.model.CountryListItem
import com.flpereira.covidworldtracker.ui.DataViewModel
import com.flpereira.covidworldtracker.ui.MainActivity
import com.flpereira.covidworldtracker.ui.MainStateEvent
import com.flpereira.covidworldtracker.util.DataState
import com.flpereira.covidworldtracker.util.snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.countries_list_fragment.*
import kotlinx.coroutines.*

//todo: o synthetic foi deprecado, recomendo aprender view binding, não confundir com databinding
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class CountriesListFragment : Fragment(R.layout.countries_list_fragment) {

    private lateinit var cAdapter: CountriesListAdapter
    lateinit var viewModel: DataViewModel
    lateinit var error: String
    var job: Job? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        viewModel.setStateEvent(MainStateEvent.GetCountriesListEvent)
        initRecyclerView()
        subscribeObservers()
        etSearch.addTextChangedListener { editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(BuildConfig.SEARCH_DELAY)
                editable?.let {
                    viewModel.filterList(editable.toString())
                }
            }
        }
    }

    private fun initRecyclerView() {
        countryRecView.apply {
            layoutManager = LinearLayoutManager(context)
            cAdapter = CountriesListAdapter()
            countryRecView.adapter = cAdapter
        }
    }

    private fun subscribeObservers() {
        with(viewModel) {
            countriesListData.observe(viewLifecycleOwner, Observer { dataSet ->
                when (dataSet) {
                    is DataState.Success<List<CountryListItem>> -> {
                        displayProgressBar(false)
                        addDataSet(dataSet.data)
                    }

                    is DataState.Error -> {
                        displayProgressBar(false)
                        displayError(dataSet.exception.message)
                    }

                    is DataState.Loading -> {
                        displayProgressBar(true)
                    }
                }
            })

            filteredCountriesListData.observe(viewLifecycleOwner, Observer { addDataSet(it) })
        }
    }

    private fun addDataSet(data: List<CountryListItem>) {
        cAdapter.submitList(data)
    }

    private fun displayError(message: String?) {
        error = message ?: "Unknow error"
        clRoot.snackbar(error)
        Log.e(">>>> NUMBER ERROR <<<<", error)
    }


    private fun displayProgressBar(isDisplayed: Boolean) {
        if (isDisplayed) {
            progressLoader.visibility = View.VISIBLE.also {
                progressLoader.start()
            }
        } else {
            progressLoader.stop().also {
                progressLoader.visibility = View.GONE
            }
        }
    }
}