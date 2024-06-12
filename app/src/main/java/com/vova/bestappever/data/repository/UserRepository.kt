package com.vova.bestappever.data.repository

import com.vova.bestappever.data.models.User
import com.vova.bestappever.data.remote.AppApi

class UserRepository(
    private  val appApi: AppApi
) {
    private val dataTest = listOf(
        User(
            id = 1,
            fio = "Зубенко Михаил Петрович",
            phoneNumber = "8 800 555 35 35",
            email = "mail.mail.ru",
            shop = "Пятерочка",
            role = "Работник",
            organization = "ООО Мегастрой",
            organizationBranch = "Ульяновск",
            organizationSubdivision = "Ульяновское"
        )
    )

    suspend fun get(id: Int): User {
        return appApi.getUser(id)[0]
    }
}