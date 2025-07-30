package com.example.sdo_project.presentation.home_navigator

import android.content.Intent
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import com.example.sdo_project.presentation.MainEvent
import com.example.sdo_project.presentation.MainState
import com.example.sdo_project.presentation.auth.AuthEvent

import com.example.sdo_project.presentation.navgraph.Routes
import com.example.sdo_project.presentation.profile.ProfileScreen
import com.example.sdo_project.presentation.profile.ProfileViewModel
import com.example.sdo_project.presentation.reset.ResetScreen
import com.example.sdo_project.presentation.reset.ResetViewModel


@Composable
fun HomeNavigatorScreen(
    mainEvent: (MainEvent) -> Unit,
    mainState: MainState
) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = Routes.Home.route) {
        composable(Routes.Home.route) {

        }

        composable(Routes.Profile.route) {
            val viewModel: ProfileViewModel = hiltViewModel()
            val state = viewModel.state.collectAsState()
            ProfileScreen(
                mainState = mainState,
                mainEvent = mainEvent,
                profileState = state.value,
                profileEvent = viewModel::onEvent
            )
        }

        composable(Routes.Participant.route) {
        }

        composable(Routes.Discipline.route) {
        }

        composable(Routes.StudentGrade.route) {
        }

        composable(Routes.TeacherGrade.route) {

        }
    }
}