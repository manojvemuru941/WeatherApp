package com.fair.weather.ui.main


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fair.weather.state.UIState
import com.fair.weather.ui.main.ext.WeatherData
import com.fair.weather.usecase.CityWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TORONTO_CODE = "4418"

@HiltViewModel
class MainViewModel @Inject constructor(
    private val cityWeatherUseCase: CityWeatherUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UIState<WeatherData>>(UIState.Loading)
    val uiState: StateFlow<UIState<WeatherData>> = _uiState

    init {
        loadData()
    }

    /**
     * TODO
     *
     */
    fun loadData() {
        viewModelScope.launch {
            cityWeatherUseCase.getAction(TORONTO_CODE)
                .flowOn(Dispatchers.IO)
                .onStart {
                    _uiState.value = UIState.Loading
                }
                .catch {
                    //show error
                    _uiState.value = UIState.Error(it)
                }
                .collect {
                    _uiState.value = UIState.Success(it)
                }
        }
    }
}