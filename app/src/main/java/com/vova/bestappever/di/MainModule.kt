package com.vova.bestappever.di

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.vova.bestappever.AppConfig
import com.vova.bestappever.data.models.User
import com.vova.bestappever.data.remote.AppApi
import com.vova.bestappever.data.remote.TokenInterceptor
import com.vova.bestappever.data.repository.ApplicationRepository
import com.vova.bestappever.data.repository.UserRepository
import com.vova.bestappever.services.Authorization
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class MainModule {

    @Provides
    @Singleton
    fun provideHttpClient(appConfig: AppConfig): AppApi {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val tokenInterceptor = TokenInterceptor(appConfig)

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(tokenInterceptor)
            .build()

        val json = Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }
        val baseUrl = "http://26.35.82.127:8000"
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()

        return retrofit.create(AppApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthorization(appApi: AppApi, appConfig: AppConfig): Authorization {
        return Authorization(appApi, appConfig)
    }

    @Provides
    @Singleton
    fun provideApplicationsRepository(api: AppApi): ApplicationRepository {
        return ApplicationRepository(api)
    }

    @Provides
    @Singleton
    fun provideUserRepository(api: AppApi): UserRepository {
        return UserRepository(api)
    }

    @Provides
    @Singleton
    fun provideAppConfig(@ApplicationContext context: Context): AppConfig {
        return AppConfig(context)
    }
}