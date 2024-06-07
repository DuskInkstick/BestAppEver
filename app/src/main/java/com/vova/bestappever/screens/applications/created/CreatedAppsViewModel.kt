package com.vova.bestappever.screens.applications.created

import androidx.lifecycle.ViewModel
import com.vova.bestappever.data.enums.ApplicationStatus
import com.vova.bestappever.data.enums.DefectCriticality
import com.vova.bestappever.data.models.Application
import com.vova.bestappever.data.repository.ApplicationRepository
import com.vova.bestappever.screens.applications.models.ApplicationsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CreatedAppsViewModel @Inject constructor(
    private val appsRepository: ApplicationRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ApplicationsState())
    val state = _state.asStateFlow()

    private var criticalityForDisplay = listOf<DefectCriticality>()

    fun setCriticalityForDisplay(criticality: List<DefectCriticality>) {
        val prev = criticalityForDisplay.toSet()
        val new = criticality.toSet()
        if (prev == new)
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
        appsRepository.update(app.copy(status = ApplicationStatus.ACCEPTED))
        updateApps()
    }

    fun onAppCanceled(app: Application) {
        appsRepository.update(app.copy(status = ApplicationStatus.CANCELED))
        updateApps()
    }

    private fun updateApps() {
        val apps = appsRepository.getNewAppsByCriticality(criticalityForDisplay)
        _state.update {
            it.copy(apps = apps)
        }
    }
}