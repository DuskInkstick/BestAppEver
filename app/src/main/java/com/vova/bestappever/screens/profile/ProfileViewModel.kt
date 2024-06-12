package com.vova.bestappever.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vova.bestappever.AppConfig
import com.vova.bestappever.data.models.User
import com.vova.bestappever.data.repository.UserRepository
import com.vova.bestappever.services.Authorization
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val appConfig: AppConfig,
    private val userRepo: UserRepository,
    private val auth: Authorization
) : ViewModel() {

    private val _navChannel = Channel<Int>()
    val navFlow = _navChannel.receiveAsFlow()

    private val _state = MutableStateFlow(ProfileState())
    val state = _state.asStateFlow()

    init {
        val id = appConfig.getUserId()
        viewModelScope.launch {
            val user = userRepo.get(id!!)
            _state.update { it.copy(user = user) }
        }
    }

    fun onLogout() {
        auth.logout()

        viewModelScope.launch {
            _navChannel.send(932746987)
        }
    }
}