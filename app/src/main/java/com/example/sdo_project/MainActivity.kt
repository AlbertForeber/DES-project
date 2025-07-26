package com.example.sdo_project

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.example.sdo_project.data.repository.GradesRepositoryImpl
import com.example.sdo_project.data.repository.SupabaseAuthRepositoryImpl
import com.example.sdo_project.data.repository.SupabaseDisciplineRepositoryImpl
import com.example.sdo_project.data.repository.SupabaseGroupRepositoryImpl
import com.example.sdo_project.domain.models.User
import com.example.sdo_project.presentation.MainViewModel
import com.example.sdo_project.presentation.navgraph.NavGraph
import com.example.sdo_project.ui.theme.SDOprojectTheme
import dagger.hilt.android.AndroidEntryPoint

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import jakarta.inject.Inject
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    // For repository testing
    @Inject
    lateinit var client: SupabaseClient
    private val viewModel by viewModels<MainViewModel>()
    //

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // For repository testing
        val authRepository = SupabaseAuthRepositoryImpl(client)
        val disciplineRepository = SupabaseDisciplineRepositoryImpl(client)
        val gradesRepository = GradesRepositoryImpl(client)
        val groupRepository = SupabaseGroupRepositoryImpl(client)

        //


        setContent {
            SDOprojectTheme {

                //Использовалось для тестирования БД + Авторизации

                LaunchedEffect(Unit) {
                    launch {
                        authRepository.signInWithEmail("kate@mail.ru", "123123")
                        Log.d("AUTH_STATUS", authRepository.getCurrentToken().getOrNull().toString())
                        val result = disciplineRepository.getDisciplines(
                            User(
                                uuid = "c7931316-491c-4415-b0fd-b6dc5208588b",
                                isTeacher = false,
                                personalCode = "fashfash",
                                surname = "1234",
                                name = "12342",
                                patronymic = "1234213",
                                departmentId = "12341423",
                                country = "12341234",
                                city = "12341234",
                            )
                        )
                        result.getOrNull()?.let { Log.d("DB_test", it.toString()) }
                    }
                }

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
                //NavGraph(viewModel)
            }
        }
    }
}
