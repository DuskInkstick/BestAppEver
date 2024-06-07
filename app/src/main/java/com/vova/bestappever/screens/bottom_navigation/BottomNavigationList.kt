package com.vova.bestappever.screens.bottom_navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Warning

object BottomNavigationList {
    val items = listOf(
        BottomNavigationItem(
            route = "emergency_applications",
            title = "Аварийные",
            selectedIcon = Icons.Filled.Warning,
            unselectedIcon = Icons.Outlined.Warning
        ),
        BottomNavigationItem(
            route = "normal_applications",
            title = "Штатные",
            selectedIcon = Icons.Filled.Info,
            unselectedIcon = Icons.Outlined.Info
        ),
        BottomNavigationItem(
            route = "accepted_applications",
            title = "Принятые",
            selectedIcon = Icons.Filled.Build,
            unselectedIcon = Icons.Outlined.Build
        ),
        BottomNavigationItem(
            route = "user_profile",
            title = "Профиль",
            selectedIcon = Icons.Filled.Person,
            unselectedIcon = Icons.Outlined.Person
        ),
    )
}