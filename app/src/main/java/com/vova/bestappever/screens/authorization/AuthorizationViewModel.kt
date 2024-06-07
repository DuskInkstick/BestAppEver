package com.vova.bestappever.screens.authorization

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vova.bestappever.AppConfig
import com.vova.bestappever.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthorizationViewModel @Inject constructor(
    val userRepository: UserRepository,
    private val appConfig: AppConfig,
): ViewModel() {
    // Это сделано тупо
    private val _navChannel = Channel<Int>()
    val navFlow = _navChannel.receiveAsFlow()

    fun onLoginClick(email: String, password: String) {
        appConfig.setCurrentUserId(1)

        viewModelScope.launch{
            _navChannel.send(287463)
        }
    }
}