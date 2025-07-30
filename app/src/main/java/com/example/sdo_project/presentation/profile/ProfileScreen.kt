package com.example.sdo_project.presentation.profile

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sdo_project.domain.models.User
import com.example.sdo_project.presentation.MainEvent
import com.example.sdo_project.presentation.MainState
import com.example.sdo_project.presentation.common.DesButton
import com.example.sdo_project.presentation.common.LoadingElement
import com.example.sdo_project.presentation.profile.components.ProfileFieldWithTitle

@Composable
fun ProfileScreen(
    mainState: MainState,
    mainEvent: (MainEvent) -> Unit,
    profileState: ProfileState,
    profileEvent: (ProfileEvent) -> Unit
) {
    val userInfo = if (mainState is MainState.Authorized) mainState.user else null

    var editMode by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        val result = handleEditResult( profileState, mainEvent )

        if (result && userInfo != null) {
            Text("${userInfo.surname} ${userInfo.name} ${userInfo.patronymic}", style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold))
            OutlinedCard(
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    Modifier.padding(10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    val fields = remember {
                        mutableStateMapOf(
                            "personalCode" to userInfo.personalCode,
                            "email" to getMail(profileState),
                            "country" to userInfo.country,
                            "city" to userInfo.city
                        )
                    }
                    //
                    Text("Detailed information", style = MaterialTheme.typography.titleLarge)

                    ProfileFieldWithTitle(
                        text = fields["personalCode"] ?: "",
                        onChange = { fields["personalCode"] = it },
                        title = "Personal Code",
                        modifier = Modifier.fillMaxWidth(),
                        editable = false,
                        active = !editMode
                    )

                    ProfileFieldWithTitle(
                        text = fields["email"] ?: "",
                        onChange = { },
                        title = "Email",
                        modifier = Modifier.fillMaxWidth(),
                        editable = false,
                        active = !editMode
                    )

                    ProfileFieldWithTitle(
                        text = fields["country"] ?: "",
                        onChange = { fields["country"] = it },
                        title = "Country",
                        modifier = Modifier.fillMaxWidth(),
                        editable = editMode
                    )

                    ProfileFieldWithTitle(
                        text = fields["city"] ?: "",
                        onChange = { fields["city"] = it },
                        title = "City",
                        modifier = Modifier.fillMaxWidth(),
                        editable = editMode
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        DesButton("Edit", { editMode = true }, active = !editMode)
                        DesButton("Save", {
                            editMode = false
                            profileEvent(
                                ProfileEvent.EditUser(
                                    userInfo
                                        .copy(
                                            city = fields["city"] ?: "",
                                            country = fields["country"] ?: ""
                                        )
                                )
                            )
                        }, active = editMode)
                    }
                }
            }
        }
        else {
            LoadingElement(modifier = Modifier.fillMaxSize())
        }

    }
}


fun getMail(profileState: ProfileState): String {
    return when (profileState) {
        is ProfileState.Error -> profileState.email
        is ProfileState.Idle -> profileState.email
        else -> ""
    }
}
@Composable
fun handleEditResult(
    profileState: ProfileState,
    mainEvent: (MainEvent) -> Unit,
): Boolean {
    return when (profileState) {
        is ProfileState.Error -> {
            Log.e("PROFILE_TEST", profileState.errorMessage)
            true
        }
        is ProfileState.Idle -> {
            true
        }
        ProfileState.Loading -> {
            false
        }
        ProfileState.SuccessEdit -> {
            mainEvent(MainEvent.Recheck)
            false
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProfileScreen() {
    ProfileScreen(
        mainState = MainState.Authorized(
            user = User(
                            uuid = "c7931316-491c-4415-b0fd-b6dc5208588b",
                            isTeacher = false,
                            personalCode = "fashfash",
                            surname = "Шаймухаметов",
                            name = "Альберт",
                            patronymic = "Илдарович",
                            departmentId = 1,
                            country = "Россия",
                            city = "Москва"
                        )
        ),
        mainEvent = {},
        profileState = ProfileState.Idle(email = "dn3412@mail.ru"),
        profileEvent = {}
    )
}

/*
    Column (
        Modifier.padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        val fields = remember { mutableStateMapOf(
                "personalCode" to userInfo.personalCode,
                "email" to profileState.email,
                "country" to userInfo.country,
                "city" to userInfo.city
            )
        }
        //
        Text("Detailed information", style = MaterialTheme.typography.titleLarge)

        Text("Personal Code", style = MaterialTheme.typography.labelLarge)
        ProfileEditableTextField(
            fields["personalCode"] ?: "",
            { fields["personalCode"] = it },
            modifier = Modifier.fillMaxWidth(),
            editable = false,
            active = !editMode
        )

        Text("Email", style = MaterialTheme.typography.labelLarge)
        ProfileEditableTextField(
            fields["email"] ?: "",
            { },
            modifier = Modifier.fillMaxWidth(),
            editable = false,
            active = !editMode
        )

        Text("Country", style = MaterialTheme.typography.labelLarge)
        ProfileEditableTextField(
            fields["country"] ?: "",
            { fields["country"] = it },
            modifier = Modifier.fillMaxWidth(),
            editable = editMode
        )

        Text("City", style = MaterialTheme.typography.labelLarge)
        ProfileEditableTextField(
            fields["city"] ?: "",
            { fields["city"] = it },
            modifier = Modifier.fillMaxWidth(),
            editable = editMode
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            DesButton("Edit", {editMode = true}, active = !editMode)
            DesButton("Save", {
                editMode = false
                profileEvent(ProfileEvent.EditUser(
                    userInfo
                        .copy(city = fields["city"] ?: "", country = fields["country"] ?: ""))
                )
            }, active = editMode)
        }
    }
 */