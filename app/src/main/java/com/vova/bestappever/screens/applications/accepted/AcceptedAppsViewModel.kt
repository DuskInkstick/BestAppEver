package com.vova.bestappever.screens.applications.accepted

import androidx.lifecycle.ViewModel
import com.vova.bestappever.AppConfig
import com.vova.bestappever.data.enums.ApplicationStatus
import com.vova.bestappever.data.models.Application
import com.vova.bestappever.data.models.Comment
import com.vova.bestappever.data.models.User
import com.vova.bestappever.data.repository.ApplicationRepository
import com.vova.bestappever.screens.applications.models.ApplicationsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AcceptedAppsViewModel @Inject constructor(
    private val currentUser: User,
    private val appsRepository: ApplicationRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ApplicationsState(title = "Принятые заявки"))
    val state = _state.asStateFlow()

    init {
        _state.update {
            it.copy(apps = getApps())
        }
    }

    fun onCommentSend(appIndex: Int, comment: String) {
        val app = state.value.apps[appIndex]

        val comments = app.comments.toMutableList()
        comments.add(Comment(currentUser.email, comment))

        appsRepository.update(
            app.copy(comments = comments)
        )
        _state.update {
            it.copy(apps = getApps())
        }
    }

    fun onCompleteAppClick(appIndex: Int) {
        val app = state.value.apps[appIndex]
        appsRepository.update(
            app.copy(status = ApplicationStatus.COMPLETED)
        )
        _state.update {
            it.copy(apps = getApps())
        }
    }

    private fun getApps(): List<Application> {
        return appsRepository.getByStatus(ApplicationStatus.ACCEPTED)
    }
}