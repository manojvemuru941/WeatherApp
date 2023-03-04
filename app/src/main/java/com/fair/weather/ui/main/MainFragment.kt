package com.fair.weather.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.fair.weather.R
import com.fair.weather.databinding.FragmentMainBinding
import com.fair.weather.state.UIState
import com.fair.weather.ui.main.ext.hide
import com.fair.weather.ui.main.ext.show

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var fragmentMainBinding: FragmentMainBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setCollectors()

        fragmentMainBinding.tryAgainBtn.setOnClickListener{
            viewModel.loadData()
        }
    }

    private fun setCollectors() {
        lifecycleScope.launchWhenCreated {
            viewModel.uiState.collect {
                fragmentMainBinding.loading.hide()
                when (it) {
                    is UIState.Loading -> {
                        fragmentMainBinding.loading.show()
                    }
                    is UIState.Error -> {
                        fragmentMainBinding.tempGroup.hide()
                        fragmentMainBinding.emptyGroup.show()
                        showErrorDialog(
                            it.throwable.message ?: getString(R.string.something_went_wrong)
                        )
                    }
                    is UIState.Success -> {
                        fragmentMainBinding.tempGroup.show()
                        fragmentMainBinding.emptyGroup.hide()
                        fragmentMainBinding.weatherText.text = it.data.currentTemp.toString()
                        fragmentMainBinding.cityTitleTv.text = it.data.cityName
                        fragmentMainBinding.stateTv.text = it.data.state
                        fragmentMainBinding.tempLowHigh.text = String.format(
                            getString(R.string.low_high_temp),
                            it.data.minTemp,
                            it.data.maxTemp
                        )
                        Glide
                            .with(this@MainFragment)
                            .load(it.data.stateUrl)
                            .placeholder(R.mipmap.ic_launcher)
                            .into(fragmentMainBinding.sateImage)
                    }
                }
            }
        }
    }

    private fun showErrorDialog(err: String) {
        val alertDialog: AlertDialog? = activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage(err)
            builder.apply {
                setPositiveButton(
                    R.string.ok
                ) { _, _ ->
                    // User clicked OK button
                }
            }

            // Create the AlertDialog
            builder.create()
        }
        alertDialog?.show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentMainBinding = FragmentMainBinding.inflate(inflater, container, false)
        return fragmentMainBinding.root
    }

}