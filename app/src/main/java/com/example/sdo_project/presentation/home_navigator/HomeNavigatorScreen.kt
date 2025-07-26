package com.example.sdo_project.presentation.home_navigator

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sdo_project.presentation.auth.AuthEvent

import com.example.sdo_project.presentation.navgraph.Routes


@Composable
fun HomeNavigatorScreen(
    navController: NavHostController,
    event: (AuthEvent) -> Unit
) {
    Text("HOME")
    Button({ event(AuthEvent.Logout) }) {

    }
    // Тут крашит из-за navController
//    NavHost(navController, startDestination = Routes.Home.route) {
//        composable(Routes.Home.route) {
//
//        }
//
//        composable(Routes.Profile.route) {
//        }
//
//        composable(Routes.Participant.route) {
//        }
//
//        composable(Routes.Discipline.route) {
//        }
//
//        composable(Routes.StudentGrade.route) {
//        }
//
//        composable(Routes.TeacherGrade.route) {
//        }
//    }
}