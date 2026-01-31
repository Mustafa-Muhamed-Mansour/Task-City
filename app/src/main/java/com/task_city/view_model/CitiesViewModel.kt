package com.task_city.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task_city.repository.CitiesRepository
import com.task_city.utils.CitiesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CitiesViewModel @Inject constructor(
    private val citiesRepository: CitiesRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(value = CitiesState())
    val uiState = _uiState.asStateFlow()


    fun fetchAllCities(countryID: String) = viewModelScope.launch {
        try {
            _uiState.update { it.copy(isLoading = true, error = "") }
            val response = citiesRepository.getCities(countryID = countryID)
            response.fold(
                onSuccess = { data ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            data = data,
                            error = ""
                        )
                    }
                },
                onFailure = { failure ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = failure.message.toString()
                        )
                    }
                }
            )
        } catch (e: Exception) {
            _uiState.update {
                it.copy(
                    isLoading = false,
                    error = e.message.toString()
                )
            }
        }
    }
}