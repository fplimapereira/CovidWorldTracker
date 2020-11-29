package com.flpereira.covidworldtracker.ui.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.flpereira.covidworldtracker.R
import com.flpereira.covidworldtracker.model.WorldCasesData
import com.flpereira.covidworldtracker.ui.DataViewModel
import com.flpereira.covidworldtracker.ui.MainActivity
import com.flpereira.covidworldtracker.ui.MainStateEvent
import com.flpereira.covidworldtracker.util.DataState
import com.flpereira.covidworldtracker.util.snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.world_fragment.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

import org.eazegraph.lib.models.PieModel

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class WorldCasesFragment: Fragment(R.layout.world_fragment) {

    lateinit var viewModel: DataViewModel
    lateinit var error: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        viewModel.setStateEvent(MainStateEvent.GetWorldDataEvent)
        subscribeObservers()
        btnTrack.setOnClickListener {
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
        root.snackbar(error)
    }


    private fun displayProgressBar(isDisplayed: Boolean){
         if (isDisplayed){
             loader.visibility = View.VISIBLE.also {
                 loader.start()
             }
         } else{
             loader.stop().also {
                 loader.stop()
                 loader.visibility = View.GONE
                 scrollStats.visibility = View.VISIBLE
             }
         }
    }

    private fun setData(data: WorldCasesData) {
        setChartData(data)
        tvCases.text = data.cases.toString()
        tvRecovered.text = data.recovered.toString()
        tvCritical.text = data.critical.toString()
        tvActive.text = data.active.toString()
        tvTodayCases.text = data.todayCases.toString()
        tvTotalDeaths.text = data.deaths.toString()
        tvTodayDeaths.text = data.todayDeaths.toString()
        tvAfeco.text = data.affectedCountries.toString()


    }

    private fun setChartData(data: WorldCasesData) {
        piechart.addPieSlice(PieModel("Cases", data.cases.toFloat(), Color.parseColor("#FFA726")))
        piechart.addPieSlice(PieModel("Recovered", data.recovered.toFloat(), Color.parseColor("#66BB6A")))
        piechart.addPieSlice(PieModel("Deaths", data.deaths.toFloat(), Color.parseColor("#EF5350")))
        piechart.addPieSlice(PieModel("Active", data.active.toFloat(), Color.parseColor("#29B6F6")))
        piechart.startAnimation()
    }
}