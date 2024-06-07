package com.vova.bestappever.data.enums

enum class RepairWorkType(val jsonValue: String) {
    DIAGNOSTIC("Диагностика"),
    REPAIR("Ремонт"),
    DIAGNOSTIC_REPAIR("Диагностика и ремонт"),
    REPLACEMENT("Замена")
}