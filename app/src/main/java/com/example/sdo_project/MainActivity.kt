package com.example.sdo_project

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.example.sdo_project.data.repository.SupabaseAuthRepositoryImpl
import com.example.sdo_project.data.repository.SupabaseDisciplineRepositoryImpl
import com.example.sdo_project.domain.models.User
import com.example.sdo_project.ui.theme.SDOprojectTheme
import dagger.hilt.android.AndroidEntryPoint

import io.github.jan.supabase.SupabaseClient
import jakarta.inject.Inject
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    // For repository testing
    @Inject
    lateinit var client: SupabaseClient
    //

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // For repository testing
        val authRepository = SupabaseAuthRepositoryImpl(client)
        val dbRepository = SupabaseDisciplineRepositoryImpl(client)
        //


        setContent {
            SDOprojectTheme {

                /* Использовалось для тестирования БД + Авторизации

                LaunchedEffect(Unit) {
                    launch {
                        val result = dbRepository.getDisciplines(
                            User(
                                uuid = "830fac75-cb1b-426d-8f5d-c5780edde534",
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
                */
            }
        }
    }
}
