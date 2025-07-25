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
import com.example.sdo_project.data.repository.GradesRepositoryImpl
import com.example.sdo_project.data.repository.MaterialRepositoryImpl
import com.example.sdo_project.data.repository.SupabaseAuthRepositoryImpl
import com.example.sdo_project.data.repository.SupabaseDisciplineRepositoryImpl
import com.example.sdo_project.domain.models.Material
import com.example.sdo_project.domain.models.MaterialSection
import com.example.sdo_project.domain.models.User
import com.example.sdo_project.domain.repository.MaterialRepository
import com.example.sdo_project.ui.theme.SDOprojectTheme
import dagger.hilt.android.AndroidEntryPoint

import io.github.jan.supabase.SupabaseClient
import jakarta.inject.Inject
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlin.time.ExperimentalTime


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    // For repository testing
    @Inject
    lateinit var client: SupabaseClient
    //

    @OptIn(ExperimentalTime::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // For repository testing
        val authRepository = SupabaseAuthRepositoryImpl(client)
        val dbRepository = SupabaseDisciplineRepositoryImpl(client)
        val gradeRepository = GradesRepositoryImpl(client)
        val materialRepository = MaterialRepositoryImpl(client)
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
                LaunchedEffect(Unit) {
                    val result1= authRepository.signInWithEmail("kate@mail.ru", "123123")
                    Log.d("KATRIN_BE", " rere ${result1}")
                    //val result = gradeRepository.getSectionGradesByStudentId("830fac75-cb1b-426d-8f5d-c5780edde534", 1,2)

                    //val result = gradeRepository.editGradeByStudentPointId("c7931316-491c-4415-b0fd-b6dc5208588b", 1,9.1f)
//                    val result = materialRepository.getMaterials(
//                        MaterialSection(
//                            id = 1,
//                            name = "",
//                            parentId = null,
//                            disciplineId = 2
//                        )
//                    )

                    val result = materialRepository.addMaterial(
                        Material(
                            name = "checkoing",
                            accessTime = kotlin.time.Clock.System.now().toString(),
                            content = "smh",
                            sectionId = 1
                        )
                    )

                    Log.d("KATRIN_BE","$result")
                }
                
            }
        }
    }
}
