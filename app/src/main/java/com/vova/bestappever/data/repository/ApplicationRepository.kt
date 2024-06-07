package com.vova.bestappever.data.repository

import com.vova.bestappever.data.enums.ApplicationStatus
import com.vova.bestappever.data.enums.DefectCriticality
import com.vova.bestappever.data.enums.RepairWorkType
import com.vova.bestappever.data.models.Application
import com.vova.bestappever.data.models.Comment

class ApplicationRepository {
    private val data = MutableList(10) {
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

    fun getAll(): List<Application> {
        return data.toList()
    }

    fun getByStatus(applicationStatus: ApplicationStatus): List<Application> {
        return data.filter { item -> item.status == applicationStatus }
    }

    fun getNewAppsByCriticality(criticality: List<DefectCriticality>): List<Application> {
        return data.filter { item ->
            item.status == ApplicationStatus.CREATED
                    && criticality.contains(item.criticality)
        }
    }

    fun update(newValue: Application): Application? {
        val oldValueI = data.indexOfFirst { app -> app.id == newValue.id }
        if (oldValueI == -1)
            return null

        data[oldValueI] = newValue
        return newValue
    }
}