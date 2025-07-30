package com.example.sdo_project.presentation.home_navigator

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sdo_project.domain.models.Discipline
import com.example.sdo_project.domain.models.User
import com.example.sdo_project.presentation.MainEvent
import com.example.sdo_project.presentation.MainState
import com.example.sdo_project.presentation.discipline.DisciplineScreen
import com.example.sdo_project.presentation.home.HomeScreen
import com.example.sdo_project.presentation.home.HomeViewModel
import com.example.sdo_project.presentation.navgraph.Routes
import com.example.sdo_project.presentation.participant.ParticipantScreen
import com.example.sdo_project.presentation.participant.ParticipantViewModel
import com.example.sdo_project.presentation.student_grade.StudentGradeScreen
import com.example.sdo_project.presentation.student_grade.StudentGradeViewModel


@Composable
fun HomeNavigatorScreen(
    mainEvent: (MainEvent) -> Unit,
    mainState: MainState
) {

    val navController = rememberNavController()
    NavHost(navController, Routes.Home.route) {

        composable(Routes.Home.route) {

            val  homeViewModel =  hiltViewModel<HomeViewModel>()
            val pageState = homeViewModel.user_loaded.value
            val user = mainState as MainState.Authorized

            HomeScreen(
                onClick = {discipline ->
                    navigateToDetails(
                        navController = navController,
                        discipline = discipline,

                    )
                },

                pageState = pageState,
                event = { homeViewModel.onEvent(user.user)},
                modifier = Modifier.fillMaxSize()
                    .padding(PaddingValues(start = 10.dp, top = 10.dp, end = 10.dp))
            )
        }

        composable(Routes.Profile.route) {
        }

        composable(Routes.Participant.route) {
            val participantViewModel: ParticipantViewModel = hiltViewModel()
            val teacher_groups = participantViewModel.groups.value
            val chosen_group = participantViewModel.chosen_group.value
            val participants = participantViewModel.participants.value
            ParticipantScreen(
                user = User(
                    uuid = "830fac75-cb1b-426d-8f5d-c5780edde532",
                    isTeacher = true,
                    name = "Frolova",
                    surname = "Ekaterina",
                    patronymic = "Yakovlevna",
                    departmentId = 1,
                    personalCode = "ФШ1819",
                    country = "Russia",
                    city = "Moscow"
                ),
                groups = teacher_groups,
                group = chosen_group,
                getStudentsByGroupId = participantViewModel::getStudentsByGroupId,
                getGroupsByTeacherUuid = participantViewModel::getGroupsByTeacherId,
                participants = participants,
                discipline = Discipline(
                    id = 2,
                    name = "Программирование на Java",
                    term = 3,
                    departmentName = "Кафедра вычислительной техники",
                    instituteName = "ИИТ",
                    courseName = "Интеллектуальные системы поддержки принятия решения"
                )
            )
        }

        composable(Routes.Discipline.route) {
            // сюда передается navigateToStudentGradeScreen()
            DisciplineScreen()
        }

        composable(Routes.StudentGrade.route) {
            val gradeVewModel : StudentGradeViewModel = hiltViewModel()
            val studentGradeState = gradeVewModel.state.value
            val pointsGrades by gradeVewModel.pointsGrades

//            navController.previousBackStackEntry?.savedStateHandle?.get<Discipline>("discipline")
//                ?.let{ discipline ->
//
//                    StudentGradeScreen(
//                        state = studentGradeState,
//                        onLoading = gradeVewModel::onLoading,
//                        onEvent = gradeVewModel::onEvent,
//                        pointsGrades = pointsGrades,
//                        discipline = discipline
//                    )
//                }

            StudentGradeScreen(
                state = studentGradeState,
                onLoading = gradeVewModel::onLoading,
                onEvent = gradeVewModel::onEvent,
                pointsGrades = pointsGrades,
                discipline = Discipline(
                    id = 2,
                    name = "Программирование на Java",
                    term = 3,
                    departmentName = "Кафедра вычислительной техники",
                    instituteName = "ИИТ",
                    courseName = "Интеллектуальные системы поддержки принятия решения"
                )
            )


        }

        composable(Routes.TeacherGrade.route) {
        }
    }
}


private fun navigateToDetails( navController: NavHostController, discipline: Discipline ){
    navController.currentBackStackEntry?.savedStateHandle?.set("discipline", discipline)
    navController.navigate(
        route = Routes.Discipline.route
    )
}

private fun navigateToStudentGradeScreen ( navController: NavHostController, discipline: Discipline){
    navController.currentBackStackEntry?.savedStateHandle?.set("discipline", discipline)
    navController.navigate(
        route = Routes.StudentGrade.route
    )
}