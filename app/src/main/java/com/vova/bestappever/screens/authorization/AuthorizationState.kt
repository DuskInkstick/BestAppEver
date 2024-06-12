package com.vova.bestappever.screens.authorization

data class AuthorizationState(
    val isError: Boolean = false,
    val errorMessage: String = "",
    val email: String = "",
    val password: String = ""
)