package com.vova.bestappever.data.enums

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class DefectCriticality(val jsonValue: String) {
    @SerialName("Аварийная")
    MAJOR("Аварийная"),

    @SerialName("Не аварийная")
    MINOR("Не аварийная"),

    UNDEFINED("Undefined")
}