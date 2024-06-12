package com.vova.bestappever.data.remote

import com.vova.bestappever.AppConfig
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor(
    private val appConfig: AppConfig
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val authToken = appConfig.getToken()

        val originalRequest = chain.request()
        val builder = originalRequest.newBuilder()

        if (authToken != null) {
            builder.header("Authorization", "Bearer $authToken")
        }

        val newRequest = builder.build()
        return chain.proceed(newRequest)
    }
}