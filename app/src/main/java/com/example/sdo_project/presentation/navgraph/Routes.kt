package com.example.sdo_project.presentation.navgraph

sealed class Routes( val route: String ) {
    // navigations
    data object AuthNavigator: Routes("authNavigator")
    data object HomeNavigator: Routes("homeNavigator")
    data object HomeNavigatorScreen: Routes("homeNavigatorScreen")
    // screens
    data object Login: Routes("login")
    data object Reset: Routes("reset")
    data object Home: Routes("home")
    data object Discipline: Routes("discipline")
    data object StudentGrade: Routes("studentGrade")
    data object TeacherGrade: Routes("teacherGrade")
    data object Participant: Routes("participant")
    data object Profile: Routes("profile")
}