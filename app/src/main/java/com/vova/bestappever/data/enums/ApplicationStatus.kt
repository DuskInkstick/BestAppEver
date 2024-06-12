package com.vova.bestappever.data.enums

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class ApplicationStatus(val jsonValue: String) {
    @SerialName("Создана")
    CREATED("Создана"),

    @SerialName("Оценена")
    ESTIMATE("Оценена"),

    @SerialName("Смета загружена")
    ESTIMATE_IS_COMPLETED("Смета загружена"),

    @SerialName("Принята мастером")
    ACCEPTED("Принята мастером"),

    @SerialName("Выполнена мастером")
    COMPLETED_BY_MASTER("Выполнена мастером"),

    @SerialName("Выполнена")
    COMPLETED("Выполнена"),
}
