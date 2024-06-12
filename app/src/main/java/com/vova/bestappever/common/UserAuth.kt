package com.vova.bestappever.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserAuthRequest(
    val email: String,
    val password: String
)

@Serializable
data class UserAuthResponse(
    @SerialName("user_email")
    val userEmail: String = "",

    @SerialName("token")
    val token: String = "",

    @SerialName("user_id")
    val userId: Int = -1,

    @SerialName("detail")
    val detail: String = ""
)