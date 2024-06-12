package com.vova.bestappever.common

import com.vova.bestappever.data.enums.ApplicationStatus
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApplicationUpdateRequest(
    @SerialName("user_id")
    val userId: Int,

    @SerialName("status")
    val status: String,

    @SerialName("infocomment")
    val comment: String
)