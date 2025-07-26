package com.example.sdo_project.presentation.navgraph

import android.content.Intent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import com.example.sdo_project.presentation.MainViewModel
import com.example.sdo_project.presentation.auth.AuthScreen
import com.example.sdo_project.presentation.auth.AuthState
import com.example.sdo_project.presentation.auth.ResetScreen
import com.example.sdo_project.presentation.home_navigator.HomeNavigatorScreen

@Composable
fun NavGraph(
    mainViewModel: MainViewModel
) {
    val navController = rememberNavController()
    val authState = mainViewModel.state.collectAsState()
    NavHost(
        navController = navController,
        startDestination = mainViewModel.startDestination
    ) {
        navigation(
            startDestination = Routes.Login.route,
            route = Routes.AuthNavigator.route,
        ) {
            composable(
                route = Routes.Login.route
            ) {
                AuthScreen(
                    event = mainViewModel::onEvent,
                    state = authState.value,
                    navigateToHome = {
                    }
                )
            }
            composable(
                route = Routes.Reset.route,
                deepLinks = listOf(
                    navDeepLink {
                        uriPattern = "project://des"
                        action = Intent.ACTION_VIEW
                    }
                )
            ) {
                ResetScreen(
                    event = mainViewModel::onEvent,
                    state = authState.value,
                )
            }
        }

        navigation(
            startDestination = Routes.HomeNavigatorScreen.route,
            route = Routes.HomeNavigator.route
        ) {
            composable(
                route = Routes.HomeNavigatorScreen.route
            ) {
                HomeNavigatorScreen(navController, mainViewModel::onEvent)
            }
        }
    }

}