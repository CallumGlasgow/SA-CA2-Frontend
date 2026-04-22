package com.example.sa_ca2_frontend.leagueTable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*
import androidx.compose.runtime.LaunchedEffect
import com.example.sa_ca2_frontend.Model.TeamResponse
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Alignment

@Composable
fun LeagueTableScreen(modifier: Modifier = Modifier) {

    var teams by remember { mutableStateOf<List<TeamResponse>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        try {
            isLoading = true
            val response = LeaderboardApiService.api.getTeams()

            if (response.isSuccessful) {
                teams = response.body() ?: emptyList()
            } else {
                error = "Failed to load teams"
            }

        } catch (e: Exception) {
            error = e.message
        }
        isLoading = false
    }


    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {

        Text(
            text = "League Table",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))

        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
            return
        }

        if (error != null) {
            Text(
                text = error!!,
                color = MaterialTheme.colorScheme.error
            )
            return
        }

        if (teams.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No teams found, be the first to join the league. Register a team on the settings page.",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            return
        }

        // header row
        Row(modifier = Modifier.fillMaxWidth()) {
            Text("Team", modifier = Modifier.weight(2f))
            Text("P", modifier = Modifier.weight(1f))
            Text("W", modifier = Modifier.weight(1f))
            Text("D", modifier = Modifier.weight(1f))
            Text("L", modifier = Modifier.weight(1f))
            Text("Pts", modifier = Modifier.weight(1f))
        }

        HorizontalDivider()

        LazyColumn {

            items(
                teams.sortedByDescending { it.points }
            ) { team ->
                val played = team.wins + team.draws + team.losses
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                ) {
                    Text(team.name, modifier = Modifier.weight(2f))
                    Text("${played}", modifier = Modifier.weight(1f))
                    Text("${team.wins}", modifier = Modifier.weight(1f))
                    Text("${team.draws}", modifier = Modifier.weight(1f))
                    Text("${team.losses}", modifier = Modifier.weight(1f))
                    Text("${team.points}", modifier = Modifier.weight(1f))
                }

                HorizontalDivider()
            }
        }
    }
}