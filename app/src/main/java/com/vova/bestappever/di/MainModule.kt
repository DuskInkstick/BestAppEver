package com.vova.bestappever.di

import android.content.Context
import androidx.annotation.Nullable
import com.vova.bestappever.AppConfig
import com.vova.bestappever.data.models.User
import com.vova.bestappever.data.repository.ApplicationRepository
import com.vova.bestappever.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MainModule {

    @Provides
    @Singleton
    fun provideApplicationsRepository(): ApplicationRepository {
        return ApplicationRepository()
    }

    @Provides
    @Singleton
    fun provideUserRepository(): UserRepository {
        return UserRepository()
    }

    @Provides
    @Singleton
    fun provideAppConfig(@ApplicationContext context: Context): AppConfig {
        return AppConfig(context)
    }

    @Provides
    fun provideCurrentUser(appConfig: AppConfig, userRepo: UserRepository): User {
        val id = appConfig.getCurrentUserId() ?: return User(id = -1)
        return userRepo.get(id)
    }
}