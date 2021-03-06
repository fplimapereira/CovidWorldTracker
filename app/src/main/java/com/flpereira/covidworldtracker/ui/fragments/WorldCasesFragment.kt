package com.flpereira.covidworldtracker.ui.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.flpereira.covidworldtracker.R
import com.flpereira.covidworldtracker.databinding.WorldFragmentBinding
import com.flpereira.covidworldtracker.model.WorldCasesData
import com.flpereira.covidworldtracker.ui.viewmodel.WorldCasesViewModel
import com.flpereira.covidworldtracker.util.DataState
import com.flpereira.covidworldtracker.util.snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.eazegraph.lib.models.PieModel

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class WorldCasesFragment: Fragment() {

    private val viewModel: WorldCasesViewModel by viewModels()
    private var _binding: WorldFragmentBinding? = null
    private val binding get() = _binding!!
    lateinit var error: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        _binding = WorldFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObservers()
        binding.btnTrack.setOnClickListener {
            findNavController().navigate(R.id.action_worldCasesFragment_to_countriesListFragment)
        }
    }

    private fun subscribeObservers(){
        viewModel.dataState.observe(viewLifecycleOwner, Observer { dataState ->
            when(dataState){
                is DataState.Success<WorldCasesData> -> {
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
                 scrollStats.visibility = View.VISIBLE
             }
         }
    }

    private fun setData(data: WorldCasesData) {
        setChartData(data)
        binding.apply {
            tvCases.text = data.cases.toString()
            tvRecovered.text = data.recovered.toString()
            tvCritical.text = data.critical.toString()
            tvActive.text = data.active.toString()
            tvTodayCases.text = data.todayCases.toString()
            tvTotalDeaths.text = data.deaths.toString()
            tvTodayDeaths.text = data.todayDeaths.toString()
            tvAfeco.text = data.affectedCountries.toString()
        }



    }

    private fun setChartData(data: WorldCasesData) {
        binding.apply {
            piechart.addPieSlice(PieModel("Cases", data.cases.toFloat(), Color.parseColor("#FFA726")))
            piechart.addPieSlice(PieModel("Recovered", data.recovered.toFloat(), Color.parseColor("#66BB6A")))
            piechart.addPieSlice(PieModel("Deaths", data.deaths.toFloat(), Color.parseColor("#EF5350")))
            piechart.addPieSlice(PieModel("Active", data.active.toFloat(), Color.parseColor("#29B6F6")))
            piechart.startAnimation()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}