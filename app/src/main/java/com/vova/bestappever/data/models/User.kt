package com.vova.bestappever.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("id")
    val id: Int = -1,

    @SerialName("user_fio")
    val fio: String = "",

    @SerialName("user_number")
    val phoneNumber: String = "",

    @SerialName("user_email")
    val email: String = "",

    @SerialName("user_shop")
    val shop: String = "",

    @SerialName("user_role")
    val role: String = "",

    @SerialName("user_organization")
    val organization: String = "",

    @SerialName("organization_subdivision")
    val organizationSubdivision: String = "",

    @SerialName("organization_branch")
    val organizationBranch: String = "",
)