package com.vova.bestappever.data.enums

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class RepairWorkType(val jsonValue: String) {
    @SerialName("Диагностика")
    DIAGNOSTIC("Диагностика"),

    @SerialName("Ремонт")
    REPAIR("Ремонт"),

    @SerialName("Диагностика и ремонт")
    DIAGNOSTIC_REPAIR("Диагностика и ремонт"),

    @SerialName("Замена")
    REPLACEMENT("Замена")
}