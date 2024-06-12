package com.vova.bestappever.screens.applications.created

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vova.bestappever.AppConfig
import com.vova.bestappever.common.ApplicationUpdateRequest
import com.vova.bestappever.data.enums.ApplicationStatus
import com.vova.bestappever.data.enums.DefectCriticality
import com.vova.bestappever.data.models.Application
import com.vova.bestappever.data.repository.ApplicationRepository
import com.vova.bestappever.screens.applications.models.ApplicationsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreatedAppsViewModel @Inject constructor(
    private val appsRepository: ApplicationRepository,
    private val appConfig: AppConfig
) : ViewModel() {

    private val _state = MutableStateFlow(ApplicationsState())
    val state = _state.asStateFlow()

    private var criticalityForDisplay = DefectCriticality.UNDEFINED

    private val userId: Int = appConfig.getUserId()!!

    fun setCriticalityForDisplay(criticality: DefectCriticality) {
        if (criticality == criticalityForDisplay)
            return

        criticalityForDisplay = criticality
        updateApps()
    }

    fun setTitle(title: String) {
        _state.update {
            it.copy(title = title)
        }
    }

    fun onAppAccepted(app: Application) {
        viewModelScope.launch {
            appsRepository.update(app.id, ApplicationUpdateRequest(userId, ApplicationStatus.ACCEPTED.jsonValue, ""))
            val apps = appsRepository.getNewAppsByCriticality(criticalityForDisplay)
            _state.update {
                it.copy(apps = apps)
            }
        }

    }

    private fun updateApps() {
        viewModelScope.launch {
            val apps = appsRepository.getNewAppsByCriticality(criticalityForDisplay)
            _state.update {
                it.copy(apps = apps)
            }
        }
    }
}