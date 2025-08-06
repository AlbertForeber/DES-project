package com.example.sdo_project.presentation.home_navigator

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sdo_project.domain.models.Discipline
import com.example.sdo_project.domain.models.MaterialSection
import com.example.sdo_project.presentation.MainEvent
import com.example.sdo_project.presentation.MainState
import com.example.sdo_project.presentation.discipline.DisciplineScreen
import com.example.sdo_project.presentation.discipline.DisciplineViewModel
import com.example.sdo_project.presentation.home.HomeScreen
import com.example.sdo_project.presentation.home.HomeViewModel
import com.example.sdo_project.presentation.material_section.MaterialSectionScreen
import com.example.sdo_project.presentation.material_section.MaterialViewModel
import com.example.sdo_project.presentation.navgraph.Routes
import com.example.sdo_project.presentation.participant.ParticipantScreen
import com.example.sdo_project.presentation.participant.ParticipantViewModel
import com.example.sdo_project.presentation.profile.ProfileScreen
import com.example.sdo_project.presentation.profile.ProfileViewModel
import com.example.sdo_project.presentation.student_grade.StudentGradeScreen
import com.example.sdo_project.presentation.student_grade.StudentGradeViewModel
import com.example.sdo_project.presentation.teacher_grade_screen.TeacherGradeScreen
import com.example.sdo_project.presentation.teacher_grade_screen.TeacherGradeViewModel


@Composable
fun HomeNavigatorScreen(
    mainEvent: (MainEvent) -> Unit,
    mainState: MainState
) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = Routes.Home.route) {
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
                modifier = Modifier
                    .fillMaxSize()
                    .padding(PaddingValues(start = 10.dp, top = 10.dp, end = 10.dp))
            )
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
            val participantViewModel: ParticipantViewModel = hiltViewModel()
            val teacher_groups = participantViewModel.groups.value
            val chosen_group = participantViewModel.chosen_group.value
            val participants = participantViewModel.participants.value

            val user_ = (mainState as MainState.Authorized).user

            navController.previousBackStackEntry?.savedStateHandle?.get<Discipline>("discipline")
                ?.let {discipline ->
                    ParticipantScreen(
                        user = user_,
                        groups = teacher_groups,
                        group = chosen_group,
                        getStudentsByGroupId = participantViewModel::getStudentsByGroupId,
                        getGroupsByTeacherUuid = participantViewModel::getGroupsByTeacherId,
                        participantsState = participants,
                        discipline = discipline
                    )
                }

        }

        composable(Routes.Discipline.route) {
            val user_ = mainState as MainState.Authorized
            val viewModel: DisciplineViewModel = hiltViewModel()
            val state_ = viewModel.state

            navController.previousBackStackEntry?.savedStateHandle?.get<Discipline>("discipline")
                ?.let{ discipline ->
                    DisciplineScreen(
                        mainState = mainState,

                        navigateToGrade =  {
                            if (user_.user.isTeacher){
                                navigateToTeacherGradeScreen(
                                    navController = navController,
                                    discipline = discipline
                                )
                            } else {
                                navigateToStudentGradeScreen(
                                    navController = navController,
                                    discipline = discipline
                                )
                            }
                        },

                        discipline = discipline,

                        navigateToParticipants = {
                            navigateToParticipantsScreen(
                                navController = navController,
                                discipline = discipline
                            )
                        },
                        state = state_.value,
                        onSectionClick = { section ->
                            navigateToMaterialSection(
                                navController = navController,
                                initParent = section
                            )

                        },
                        onLoading = viewModel::onLoading
                    )
                }

        }

        composable(Routes.StudentGrade.route) {
            val gradeVewModel : StudentGradeViewModel = hiltViewModel()
            val studentGradeState = gradeVewModel.state.value
            val pointsGrades by gradeVewModel.pointsGrades

            navController.previousBackStackEntry?.savedStateHandle?.get<Discipline>("discipline")
                ?.let{ discipline ->
                    StudentGradeScreen(
                        state = studentGradeState,
                        onLoading = gradeVewModel::onLoading,
                        onEvent = gradeVewModel::onEvent,
                        pointsGrades = pointsGrades,
                        discipline = discipline,
                        mainState = mainState
                    )
                }
//
//            StudentGradeScreen(
//                state = studentGradeState,
//                onLoading = gradeVewModel::onLoading,
//                onEvent = gradeVewModel::onEvent,
//                pointsGrades = pointsGrades,
//                discipline = Discipline(
//                    id = 2,
//                    name = "Программирование на Java",
//                    term = 3,
//                    departmentName = "Кафедра вычислительной техники",
//                    instituteName = "ИИТ",
//                    courseName = "Интеллектуальные системы поддержки принятия решения"
//                ),
//                mainState = mainState
//            )


        }

        composable(Routes.TeacherGrade.route) {
            navController.previousBackStackEntry?.savedStateHandle?.get<Discipline>("discipline")?.let { discipline ->
                val viewModel: TeacherGradeViewModel = hiltViewModel()
                val state = viewModel.state.collectAsState()
                val listState = viewModel.listState.collectAsState()
                TeacherGradeScreen(
                    event = viewModel::onEvent,
                    disciplineId = discipline.id,
                    mainState = mainState,
                    teacherGradeState = state.value,
                    pointListState = listState.value
                )
            }
        }

        composable(Routes.MaterialSection.route) {
            navController.previousBackStackEntry
                ?.savedStateHandle?.get<MaterialSection>("materialSection")?.let { section ->
                    val viewModel: MaterialViewModel = hiltViewModel()
                    val state = viewModel.state.collectAsState()
                    val backStack = viewModel.backStack
                    Log.d("MT_SCREEN_TEST", "$state, $backStack")
                    MaterialSectionScreen(
                        section,
                        event = viewModel::onEvent,
                        state = state.value,
                        backStack = backStack
                    ) {
                        navController.navigateUp()
                    }
            }
        }
    }
}

private fun navigateToMaterialSection( navController: NavHostController, initParent: MaterialSection ) {
    navController.currentBackStackEntry?.savedStateHandle?.set("materialSection", initParent)
    navController.navigate(Routes.MaterialSection.route)
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

private fun navigateToTeacherGradeScreen ( navController: NavHostController, discipline: Discipline){
    navController.currentBackStackEntry?.savedStateHandle?.set("discipline", discipline)
    navController.navigate(
        route = Routes.TeacherGrade.route
    )
}

private fun navigateToParticipantsScreen ( navController: NavHostController, discipline: Discipline){
    navController.currentBackStackEntry?.savedStateHandle?.set("discipline", discipline)
    navController.navigate(
        route = Routes.Participant.route
    )
}