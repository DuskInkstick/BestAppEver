package com.vova.bestappever.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Comment(
    @SerialName("user_email")
    val userEmail: String = "",
    @SerialName("infocomment")
    val comment: String = ""
)