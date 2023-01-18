package com.example.pirates_challenge.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pirates_challenge.R
import com.example.pirates_challenge.common.exceptions.LocationPermissionException
import com.example.pirates_challenge.domain.interactor.ForecastInteractor
import com.example.pirates_challenge.domain.model.forecast.DailyForecast
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(
    private val forecastInteractor: ForecastInteractor
) : ViewModel() {

    private val _forecastFlow = MutableStateFlow<DailyForecastUiState>(
        DailyForecastUiState.Success(
            emptyList()
        )
    )
    val forecastFlow: StateFlow<DailyForecastUiState> = _forecastFlow

    private val _errorFlow = MutableStateFlow(-1)
    val errorFlow: StateFlow<Int> = _errorFlow

    fun getWeatherData() {
        viewModelScope.launch(SupervisorJob() + Dispatchers.IO) {
            runCatching {
                forecastInteractor.getDailyForecast().let {
                    _forecastFlow.value = DailyForecastUiState.Success(it)
                }
            }.onFailure {
                _forecastFlow.value = DailyForecastUiState.Error(it)
            }
        }
    }

    fun handleError(t: Throwable) {
        when (t) {
            is LocationPermissionException -> {
                _errorFlow.value = R.string.location_permission_error_message
            }
            else -> {
                R.string.default_error_message
            }
        }
    }

}

sealed class DailyForecastUiState {
    data class Success(val forecasts: List<DailyForecast>) : DailyForecastUiState()
    data class Error(val throwable: Throwable) : DailyForecastUiState()
}


