package com.vova.bestappever.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vova.bestappever.AppConfig
import com.vova.bestappever.data.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val appConfig: AppConfig,
    val user: User,
) : ViewModel() {

    private val _navChannel = Channel<Int>()
    val navFlow = _navChannel.receiveAsFlow()

    fun onLogout() {
        appConfig.setCurrentUserId(-1)
        viewModelScope.launch {
            _navChannel.send(932746987)
        }
    }
}