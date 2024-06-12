package com.vova.bestappever.services

import com.vova.bestappever.AppConfig
import com.vova.bestappever.common.UserAuthRequest
import com.vova.bestappever.data.remote.AppApi

class Authorization(
    private val appApi: AppApi,
    private val appConfig: AppConfig
) {
    fun isAuthorizationRequire(): Boolean {
        val token = appConfig.getToken() ?: return true

        return false
    }

    suspend fun login(email: String, password: String): String? {
        val loginResponse = appApi.login(UserAuthRequest(email, password))

        if(loginResponse.detail.isNotEmpty())
            return loginResponse.detail

        appConfig.setToken(loginResponse.token)
        appConfig.setUserId(loginResponse.userId)
        appConfig.setUserEmail(loginResponse.userEmail)
        return null
    }

    fun logout() {
        appConfig.setToken("")
        appConfig.setUserId(-1)
        appConfig.setUserEmail("")
    }
}