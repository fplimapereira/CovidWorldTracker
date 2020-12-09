package com.flpereira.covidworldtracker.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.flpereira.covidworldtracker.BuildConfig
import com.flpereira.covidworldtracker.adapter.CountriesListAdapter
import com.flpereira.covidworldtracker.databinding.CountriesListFragmentBinding
import com.flpereira.covidworldtracker.model.CountryListItem
import com.flpereira.covidworldtracker.ui.viewmodel.CountriesViewModel
import com.flpereira.covidworldtracker.util.DataState
import com.flpereira.covidworldtracker.util.snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.countries_list_fragment.*
import kotlinx.coroutines.*

//todo: o synthetic foi deprecado, recomendo aprender view binding, não confundir com databinding
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class CountriesListFragment : Fragment() {

    private var _binding: CountriesListFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var cAdapter: CountriesListAdapter
    private val viewModel: CountriesViewModel by viewModels()
    lateinit var error: String
    var job: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        _binding = CountriesListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}