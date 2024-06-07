package com.vova.bestappever.data.repository

import com.vova.bestappever.data.models.User

class UserRepository {
    private val data = listOf(
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

    fun get(id: Int): User {
        return data.first { user -> user.id == id }
    }
}