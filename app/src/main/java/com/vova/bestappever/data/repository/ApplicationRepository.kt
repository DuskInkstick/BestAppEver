package com.vova.bestappever.data.repository

import com.vova.bestappever.common.ApplicationUpdateRequest
import com.vova.bestappever.data.enums.ApplicationStatus
import com.vova.bestappever.data.enums.DefectCriticality
import com.vova.bestappever.data.enums.RepairWorkType
import com.vova.bestappever.data.models.Application
import com.vova.bestappever.data.models.Comment
import com.vova.bestappever.data.remote.AppApi

class ApplicationRepository(
    private val appApi: AppApi
) {
    private val dataTest = MutableList(10) {
        Application(
            id = it,
            description = "Ничего не работает каак включить кампутеер памагите я хочу домой",
            causeOfFailure = "Я сломать",
            status = ApplicationStatus.CREATED,
            dateOfCreation = "01.05.2024",
            dateOfCompletion = "11.06.2024",
            directionOfWork = "Электрика",
            typeOfRepairWork = RepairWorkType.DIAGNOSTIC,
            criticality = if (it % 2 == 0) DefectCriticality.MAJOR else DefectCriticality.MINOR,
            expenseApprovalStatus = "Подтверждено",
            comments = listOf(
                Comment(
                    "vasya228@mail.ru",
                    "Привет как дела как погода азаза"
                ),
                Comment(
                    "workerkerker@mail.ru",
                    "F"
                )
            )
        )
    }

    private val emergencyApps = mutableListOf<Application>()
    private val normalApps = mutableListOf<Application>()

    fun getAll(): List<Application> {
        return dataTest.toList()
    }

    suspend fun getByStatus(applicationStatus: ApplicationStatus): List<Application> {
        loadApps()

        return emergencyApps
            .filter { it.status == applicationStatus }
            .plus(normalApps.filter { it.status == applicationStatus })
    }

    suspend fun getNewAppsByCriticality(criticality: DefectCriticality): List<Application> {
        loadApps()
        val ignoreStatus = setOf(
            ApplicationStatus.ACCEPTED,
            ApplicationStatus.COMPLETED_BY_MASTER,
            ApplicationStatus.COMPLETED
        )
        return when (criticality) {
            DefectCriticality.MAJOR -> {
                emergencyApps.filter { !ignoreStatus.contains(it.status) }
            }

            DefectCriticality.MINOR -> {
                normalApps.filter { !ignoreStatus.contains(it.status) }
            }

            DefectCriticality.UNDEFINED -> {
                listOf()
            }
        }
    }

    suspend fun update(appId: Int, data: ApplicationUpdateRequest) {
        appApi.update(appId, data)
        clearApps()
        loadApps()
    }

    private suspend fun loadApps() {
        if (emergencyApps.isEmpty()) {
            emergencyApps.addAll(appApi.getEmergencyApplications())
        }
        if (normalApps.isEmpty()) {
            normalApps.addAll(appApi.getNormalApplications())
        }
    }

    private fun clearApps() {
        emergencyApps.clear()
        normalApps.clear()
    }
}