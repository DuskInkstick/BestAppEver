package com.vova.bestappever.screens.applications.accepted

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vova.bestappever.AppConfig
import com.vova.bestappever.common.ApplicationUpdateRequest
import com.vova.bestappever.data.enums.ApplicationStatus
import com.vova.bestappever.data.models.Application
import com.vova.bestappever.data.models.Comment
import com.vova.bestappever.data.models.User
import com.vova.bestappever.data.repository.ApplicationRepository
import com.vova.bestappever.screens.applications.models.ApplicationsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AcceptedAppsViewModel @Inject constructor(
    private val appConfig: AppConfig,
    private val appsRepository: ApplicationRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ApplicationsState(title = "Принятые заявки"))
    val state = _state.asStateFlow()

    private val userId: Int

    init {
        getApps()
        userId = appConfig.getUserId()!!
    }

    fun onCommentSend(appIndex: Int, comment: String) {
        val app = state.value.apps[appIndex]

        val comments = app.comments.toMutableList()
        comments.add(Comment(appConfig.getUserEmail()!!, comment))

        viewModelScope.launch {
            appsRepository.update(
                app.id,
                ApplicationUpdateRequest(userId, app.status.jsonValue, comment)
            )
            getApps()
        }
    }

    fun onCompleteAppClick(appIndex: Int) {
        val app = state.value.apps[appIndex]

        viewModelScope.launch {
            appsRepository.update(
                app.id,
                ApplicationUpdateRequest(
                    userId,
                    ApplicationStatus.COMPLETED_BY_MASTER.jsonValue,
                    ""
                )
            )
            getApps()
        }
    }

    private fun getApps() {
        viewModelScope.launch {
            val apps = appsRepository.getByStatus(ApplicationStatus.ACCEPTED)
            _state.update {
                it.copy(apps = apps)
            }
        }
    }
}