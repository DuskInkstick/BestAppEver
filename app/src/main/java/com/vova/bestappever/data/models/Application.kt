package com.vova.bestappever.data.models

import com.vova.bestappever.data.enums.ApplicationStatus
import com.vova.bestappever.data.enums.DefectCriticality
import com.vova.bestappever.data.enums.RepairWorkType

data class Application(
    val id: Int,
    val description: String,
    val causeOfFailure: String,
    val status: ApplicationStatus,
    val dateOfCreation: String,
    val dateOfCompletion: String,
    val directionOfWork: String,
    val typeOfRepairWork: RepairWorkType,
    val criticality: DefectCriticality,
    val expenseApprovalStatus: String,
    val comments: List<Comment>
)