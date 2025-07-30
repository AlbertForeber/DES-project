package com.example.sdo_project.presentation.navgraph

import android.content.Intent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import com.example.sdo_project.presentation.MainViewModel
import com.example.sdo_project.presentation.auth.AuthScreen
import com.example.sdo_project.presentation.home_navigator.HomeNavigatorScreen

@Composable
fun NavGraph(
    mainViewModel: MainViewModel,
) {
    val navController = rememberNavController()
    val mainState = mainViewModel.state.collectAsState()

    val isReset = remember { mutableStateOf(false) }

    NavHost(
        navController = navController,
        startDestination =
            if (isReset.value)
                Routes.AuthNavigator.route
            else mainViewModel.startDestination
    ) {
        navigation(
            startDestination = Routes.Login.route,
            route = Routes.AuthNavigator.route,
        ) {
            composable(
                route = Routes.Login.route
            ) {
                AuthScreen(
                    event = {}
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
                Text("Reset", modifier = Modifier.fillMaxSize().statusBarsPadding())
                // TODO
            }
        }

        navigation(
            startDestination = Routes.HomeNavigatorScreen.route,
            route = Routes.HomeNavigator.route
        ) {
            composable(
                route = Routes.HomeNavigatorScreen.route
            ) {
                HomeNavigatorScreen(
                    mainState =  mainState.value,
                    mainEvent = mainViewModel::onEvent
                )
            }
        }
    }

}