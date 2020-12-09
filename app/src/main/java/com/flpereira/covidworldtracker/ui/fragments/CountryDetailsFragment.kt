package com.flpereira.covidworldtracker.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.flpereira.covidworldtracker.R
import com.flpereira.covidworldtracker.databinding.CountryDetailFragmentBinding


class CountryDetailsFragment: Fragment(R.layout.country_detail_fragment) {

    private var _binding: CountryDetailFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = CountryDetailFragmentBinding.bind(view)

    }
}