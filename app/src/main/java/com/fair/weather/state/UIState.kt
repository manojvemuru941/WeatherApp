package com.fair.weather.state

sealed interface UIState<out T> {
    data class Success<T>(val data: T) : UIState<T>
    data class Error(val throwable: Throwable) : UIState<Nothing>
    object Loading : UIState<Nothing>
}