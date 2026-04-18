package com.example.sa_ca2_frontend.team

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.text.style.TextAlign

@Composable
fun CreateTeamScreen(
    modifier: Modifier = Modifier,
    onCreateSuccess: () -> Unit
) {

    var teamName by remember { mutableStateOf("") }
    var playerName by remember { mutableStateOf("") }
    val players = remember { mutableStateListOf<String>() }


    Column(
        modifier = modifier.fillMaxSize().padding(16.dp)
    ) {

        Text(
            text = "Create Team",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = teamName,
            onValueChange = { teamName = it },
            label = { Text("Team Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        // players input
        Row(modifier = Modifier.fillMaxWidth()) {

            OutlinedTextField(
                value = playerName,
                onValueChange = { playerName = it },
                label = { Text("Player Name") },
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = {
                    if (playerName.isNotBlank()) {
                        players.add(playerName)
                        playerName = ""
                    }
                }
            ) {
                Text("Add")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Players", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            items(players) { player ->

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(player)

                    TextButton(
                        onClick = {
                            players.remove(player)
                        }
                    ) {
                        Text("Remove")
                    }
                }

                Divider()
            }
        }

        Spacer(modifier = Modifier.height(16.dp))


        Button(
            onClick = {
                // backend logic
                onCreateSuccess()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Create Team")
        }
    }
}