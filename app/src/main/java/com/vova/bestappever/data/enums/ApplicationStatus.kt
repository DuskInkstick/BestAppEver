package com.vova.bestappever.data.enums

enum class ApplicationStatus(val jsonValue: String) {
    CREATED("Создана"),
    ACCEPTED("Принята"),
    COMPLETED("Выполнена"),
    CANCELED("Отменена")
}