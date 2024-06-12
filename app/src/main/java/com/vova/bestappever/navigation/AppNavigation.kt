package com.vova.bestappever.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.vova.bestappever.data.enums.DefectCriticality
import com.vova.bestappever.screens.applications.accepted.AcceptedAppsScreen
import com.vova.bestappever.screens.applications.accepted.AcceptedAppsViewModel
import com.vova.bestappever.screens.applications.created.NotAcceptedAppsScreen
import com.vova.bestappever.screens.applications.created.CreatedAppsViewModel
import com.vova.bestappever.screens.authorization.AuthorizationScreen
import com.vova.bestappever.screens.authorization.AuthorizationViewModel
import com.vova.bestappever.screens.profile.ProfileScreen
import com.vova.bestappever.screens.profile.ProfileViewModel
import java.util.Date

@Composable
fun AppNavigation(
    navController: NavHostController,
    padding: PaddingValues,
    startWithLogin: Boolean = false
) {
    NavHost(
        navController = navController,
        startDestination = if (startWithLogin) "login" else "accepted_applications",
        modifier = Modifier.padding(paddingValues = padding)
    ) {
        composable("emergency_applications") {
            val viewModel = hiltViewModel<CreatedAppsViewModel>()
            val state by viewModel.state.collectAsState()

            viewModel.setTitle("Аварийные заявки")
            viewModel.setCriticalityForDisplay(DefectCriticality.MAJOR)

            NotAcceptedAppsScreen(
                state = state,
                onAppAccepted = viewModel::onAppAccepted
            )
        }

        composable("normal_applications") {
            val viewModel = hiltViewModel<CreatedAppsViewModel>()
            val state by viewModel.state.collectAsState()

            viewModel.setTitle("Штатные заявки")
            viewModel.setCriticalityForDisplay(DefectCriticality.MINOR)

            NotAcceptedAppsScreen(
                state = state,
                onAppAccepted = viewModel::onAppAccepted
            )
        }

        composable("accepted_applications") {
            val viewModel = hiltViewModel<AcceptedAppsViewModel>()
            val state by viewModel.state.collectAsState()

            AcceptedAppsScreen(
                state = state,
                onCompleteAppClick = viewModel::onCompleteAppClick,
                onCommentSend = viewModel::onCommentSend
            )
        }

        composable("user_profile") {
            val viewModel = hiltViewModel<ProfileViewModel>()

            ObserveAsEvents(flow = viewModel.navFlow) {
                navController.navigate("login") {
                    popUpTo(navController.graph.id) {
                        inclusive = true
                    }
                }
            }
            ProfileScreen(
                viewModel.state.collectAsState().value,
                viewModel::onLogout
            )
        }

        composable("login") {
            val viewModel = hiltViewModel<AuthorizationViewModel>()

            ObserveAsEvents(flow = viewModel.navFlow) {
                navController.navigate("accepted_applications") {
                    popUpTo(navController.graph.id) {
                        inclusive = true
                    }
                }
            }
            AuthorizationScreen(
                state = viewModel.state.collectAsState().value,
                onLoginClick = viewModel::onLoginClick,
                onPasswordChanged = viewModel::onPasswordChange,
                onEmailChanged = viewModel::onEmailChange
            )
        }
    }
}