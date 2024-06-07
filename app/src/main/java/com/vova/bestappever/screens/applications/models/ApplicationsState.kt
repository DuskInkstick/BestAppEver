package com.vova.bestappever.screens.applications.models

import com.vova.bestappever.data.models.Application

data class ApplicationsState(
    val title: String = "",
    val apps: List<Application> = listOf()
)