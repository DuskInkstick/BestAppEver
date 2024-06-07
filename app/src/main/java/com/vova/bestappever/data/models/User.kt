package com.vova.bestappever.data.models

data class User(
    val id: Int = -1,
    val fio: String = "",
    val phoneNumber: String = "",
    val email: String = "",
    val shop: String = "",
    val role: String = "",
    val organization: String = "",
    val organizationSubdivision: String = "",
    val organizationBranch: String = "",
)