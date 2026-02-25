package com.aura.gamifiedlifeos.ui.dashboard

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DashboardViewModel : ViewModel() {
    private val _state = MutableStateFlow(DashboardUiState())
    val state: StateFlow<DashboardUiState> = _state.asStateFlow()

    fun toggleLowEnergyMode(enabled: Boolean) {
        _state.value = _state.value.copy(isLowEnergyMode = enabled)
    }
}
