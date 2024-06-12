package com.vova.bestappever.data.models

import com.vova.bestappever.data.enums.ApplicationStatus
import com.vova.bestappever.data.enums.DefectCriticality
import com.vova.bestappever.data.enums.RepairWorkType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Application(
    @SerialName("application_id")
    val id: Int = -1,

    @SerialName("description")
    val description: String = "",

    @SerialName("causeoffailure")
    val causeOfFailure: String = "",

    @SerialName("status")
    val status: ApplicationStatus = ApplicationStatus.CREATED,

    @SerialName("datecreate")
    val dateOfCreation: String = "",

    @SerialName("dateofcompletion")
    val dateOfCompletion: String? = null,

    @SerialName("directionofwork")
    val directionOfWork: String = "",

    @SerialName("typeofrepairwork")
    val typeOfRepairWork: RepairWorkType = RepairWorkType.REPAIR,

    @SerialName("criticalityofthedefect")
    val criticality: DefectCriticality = DefectCriticality.UNDEFINED,

    @SerialName("expenseapprovalstatus")
    val expenseApprovalStatus: String = "",

    @SerialName("comments")
    val comments: List<Comment> = listOf()
)