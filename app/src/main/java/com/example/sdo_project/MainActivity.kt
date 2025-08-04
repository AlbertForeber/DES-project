package com.example.sdo_project

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sdo_project.data.repository.GradesRepositoryImpl
import com.example.sdo_project.data.repository.SupabaseAuthRepositoryImpl
import com.example.sdo_project.data.repository.SupabaseDisciplineRepositoryImpl
import com.example.sdo_project.data.repository.SupabaseGroupRepositoryImpl
import com.example.sdo_project.domain.models.MaterialSection
import com.example.sdo_project.domain.models.User
import com.example.sdo_project.presentation.MainState
import com.example.sdo_project.presentation.MainViewModel
import com.example.sdo_project.presentation.material_section.MaterialSectionScreen
import com.example.sdo_project.presentation.material_section.MaterialViewModel
import com.example.sdo_project.presentation.teacher_grade_screen.TeacherGradeScreen
import com.example.sdo_project.presentation.teacher_grade_screen.TeacherGradeViewModel
import com.example.sdo_project.ui.theme.SDOprojectTheme
import dagger.hilt.android.AndroidEntryPoint

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.handleDeeplinks
import io.github.jan.supabase.auth.providers.builtin.Email
import jakarta.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    // For repository testing
    @Inject
    lateinit var client: SupabaseClient
    private val viewModel by viewModels<MainViewModel>()
    //

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        client.handleDeeplinks(intent)


        // For repository testing
        val authRepository = SupabaseAuthRepositoryImpl(client)
        val disciplineRepository = SupabaseDisciplineRepositoryImpl(client)
        val gradesRepository = GradesRepositoryImpl(client)
        val groupRepository = SupabaseGroupRepositoryImpl(client)

        //


        setContent {
            SDOprojectTheme {

                //Использовалось для тестирования БД + Авторизации

//                LaunchedEffect(Unit) {
//                    launch {
//                        authRepository.signInWithEmail("kate@mail.ru", "123123")
//                        Log.d("AUTH_STATUS", authRepository.getCurrentToken().getOrNull().toString())
//                        val result = disciplineRepository.getDisciplines(
//                            User(
//                                uuid = "c7931316-491c-4415-b0fd-b6dc5208588b",
//                                isTeacher = false,
//                                personalCode = "fashfash",
//                                surname = "1234",
//                                name = "12342",
//                                patronymic = "1234213",
//                                departmentId = "12341423",
//                                country = "12341234",
//                                city = "12341234",
//                            )
//                        )
//                        result.getOrNull()?.let { Log.d("DB_test", it.toString()) }
//                    }
//                }

//                LaunchedEffect(Unit) {
//                    launch {
//                        Log.d("AUTH_STATUS", authRepository.getCurrentToken().getOrNull().toString())
//                         val result = groupRepository.getGroupsByTeacherUuid(
//                            "830fac75-cb1b-426d-8f5d-c5780edde532",
//                            disciplineId = 2
//                         )
//                        result.exceptionOrNull()?.let { Log.d("DB_test", it.toString()) }
//                        result.getOrNull()?.let { Log.d("DB_test", it.toString()) }
//                    }
//                }
                //


                LaunchedEffect(Unit) {
                    client.auth.signInWith(Email) {
                        email = "kate@mail.ru"
                        password = "123123"
                    }
                }

//                val techVM: TeacherGradeViewModel = hiltViewModel()
//                val state = techVM.state.collectAsState()
//                val listState = techVM.listState.collectAsState()
//                Log.d("LIST_TEST", listState.toString())
//                TeacherGradeScreen(
//                    event = techVM::onEvent,
//                    disciplineId = 2,
//                    mainState = MainState.Authorized(
//                        user = User(
//                            uuid = "830fac75-cb1b-426d-8f5d-c5780edde532",
//                            isTeacher = true,
//                            personalCode = "fashfash",
//                            surname = "1234",
//                            name = "12342",
//                            patronymic = "1234213",
//                            departmentId = 1,
//                            country = "12341234",
//                            city = "12341234",
//                        )
//                    ),
//                    teacherGradeState = state.value,
//                    pointListState = listState.value
//                )

                val matSecViewModel: MaterialViewModel = hiltViewModel()
                val state = matSecViewModel.state.collectAsState()
                val backStack = matSecViewModel.backStack
                MaterialSectionScreen(
                    initialParent = MaterialSection(
                        id = 6,
                        name = "NestedFolder",
                        parentId = 3,
                        disciplineId = 2
                    ),
                    event = matSecViewModel::onEvent,
                    state = state.value,
                    backStack = backStack
                ) {}
                //NavGraph(viewModel)
            }
        }
    }
}
