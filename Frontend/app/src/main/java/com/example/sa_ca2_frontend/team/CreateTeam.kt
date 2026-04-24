package com.example.sa_ca2_frontend.team

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import com.example.sa_ca2_frontend.R
import kotlinx.coroutines.launch

@Composable
fun CreateTeamScreen(
    modifier: Modifier = Modifier,
    onCreateSuccess: () -> Unit
) {

    var teamName by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()
    var error by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp)
    ) {

        Text(
            text = stringResource(id = R.string.CreateTeamTitle),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = teamName,
            onValueChange = {
                teamName = it
                error = null
                            },
            label = { Text(stringResource(id = R.string.CreateTeamTextLabl)) },
            modifier = Modifier.fillMaxWidth()
        )
        if (error != null) {
            Text(
                text = error!!,
                color = MaterialTheme.colorScheme.error
            )

            Spacer(modifier = Modifier.height(8.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                scope.launch {

                    if (teamName.isBlank()) {
                        error = "Team name required"
                        return@launch
                    }

                    try {
                        val response = TeamApiService.api.createTeam(
                            CreateTeamRequest(
                                name = teamName
                            )
                        )

                        if (response.isSuccessful) {
                            onCreateSuccess()
                        } else {
                            error = "Failed to create team"
                        }

                    } catch (e: Exception) {
                        error = e.message
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(id = R.string.CreateTeamButton))
        }
    }
}