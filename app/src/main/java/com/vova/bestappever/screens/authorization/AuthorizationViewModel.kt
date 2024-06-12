package com.vova.bestappever.screens.authorization

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class AuthorizationViewModel @Inject constructor(
    private val auth: Authorization,
) : ViewModel() {
    private val _state = MutableStateFlow(AuthorizationState())
    val state = _state.asStateFlow()

    // Это сделано тупо
    private val _navChannel = Channel<Int>()
    val navFlow = _navChannel.receiveAsFlow()

    fun onLoginClick() {

        viewModelScope.launch {
            val message = auth.login(state.value.email, state.value.password)
            if (message != null) {
                _state.update { it.copy(isError = true, errorMessage = message) }
            } else {
                _navChannel.send(287463)
            }
        }
    }

    fun onPasswordChange(password: String) {
        _state.update { it.copy(password = password, isError = false) }
    }

    fun onEmailChange(email: String) {
        _state.update { it.copy(email = email, isError = false) }
    }
}