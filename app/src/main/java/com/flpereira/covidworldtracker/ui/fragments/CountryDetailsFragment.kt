package com.flpereira.covidworldtracker.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.flpereira.covidworldtracker.databinding.CountryDetailFragmentBinding
import com.flpereira.covidworldtracker.model.CountriesData
import com.flpereira.covidworldtracker.ui.viewmodel.CountryViewModel
import com.flpereira.covidworldtracker.util.DataState
import com.flpereira.covidworldtracker.util.getUrlImage
import com.flpereira.covidworldtracker.util.snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.util.concurrent.TimeUnit

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class CountryDetailsFragment: Fragment() {

    private val viewModel: CountryViewModel by viewModels()
    private var _binding: CountryDetailFragmentBinding? = null
    private val binding get() = _binding!!
    private val args: CountryDetailsFragmentArgs by navArgs()
    lateinit var error: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = CountryDetailFragmentBinding.inflate(inflater, container, false)
        sharedElementEnterTransition =
            TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
        postponeEnterTransition(250, TimeUnit.MILLISECONDS)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //_binding = CountryDetailFragmentBinding.bind(view) caso não utilize o método onCreateView
        var countryName = args.country
        viewModel.getData(countryName)
        subscribeObservers()

    }

    private fun subscribeObservers(){
        viewModel.countryData.observe(viewLifecycleOwner, Observer { dataState ->
            when(dataState){
                is DataState.Success<CountriesData> -> {
                    displayProgressBar(false)
                    setData(dataState.data)
                }

                is DataState.Error -> {
                    displayProgressBar(false)
                    displayError(dataState.exception.message)
                }

                is DataState.Loading -> {
                    displayProgressBar(true)
                }
            }
        })
    }

    private fun displayError(message: String?){
        error = message ?: "Unknow error"
        Log.e(">>>> Details Error <<<<", error)
        binding.root.snackbar(error)
    }


    private fun displayProgressBar(isDisplayed: Boolean){
        if (isDisplayed){
            binding.apply {
                loader.visibility = View.VISIBLE
                loader.start()
            }
        } else{
            binding.apply {
                loader.stop()
                loader.visibility = View.GONE
            }
        }
    }

    private fun setData(data: CountriesData){
        binding.apply {
            flagView.getUrlImage(data.countryInfo.flag)
            flagView.transitionName = args.country
            tvCases.text = data.cases.toString()
            tvRecovered.text = data.recovered.toString()
            tvCritical.text = data.critical.toString()
            tvActives.text = data.active.toString()
            tvTodayCases.text = data.todayCases.toString()
            tvTotalDeaths.text = data.deaths.toString()
            tvTodayDeaths.text = data.todayDeaths.toString()
        }
    }

}