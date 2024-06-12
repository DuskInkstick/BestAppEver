package com.vova.bestappever

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.vova.bestappever.data.models.User
import com.vova.bestappever.navigation.AppNavigation
import com.vova.bestappever.screens.bottom_navigation.BottomNavigationBar
import com.vova.bestappever.services.Authorization
import com.vova.bestappever.ui.theme.BestAppEverTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var auth: Authorization

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            BestAppEverTheme(
                dynamicColor = false
            ) {
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomNavigationBar(navController)
                    }
                ) { innerPadding ->
                    AppNavigation(navController, innerPadding, auth.isAuthorizationRequire())
                }
            }
        }
    }
}
