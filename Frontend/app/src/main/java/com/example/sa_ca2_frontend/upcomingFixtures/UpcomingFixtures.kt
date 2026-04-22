package com.example.sa_ca2_frontend.upcomingFixtures

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import com.example.sa_ca2_frontend.leagueTable.LeaderboardApiService
import com.example.sa_ca2_frontend.Model.TeamResponse

fun formatMatchDate(dateTime: String): Pair<String, String> {
    val parts = dateTime.split("T")
    val date = parts[0] // 2026-04-23
    val time = parts[1].take(5)

    return date to time
}

@Composable
fun UpcomingFixturesScreen(modifier: Modifier = Modifier) {

    var fixtures by remember { mutableStateOf<List<FixtureResponse>>(emptyList()) }
    var teams by remember { mutableStateOf<List<TeamResponse>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }
    var showAll by remember { mutableStateOf(false) }
    val visibleFixtures = if (showAll) fixtures else fixtures.take(3)

    LaunchedEffect(Unit) {
        try {
            val fixtureResponse = FixtureApiService.api.getFixtures()
            val teamResponse = LeaderboardApiService.api.getTeams()

            if (fixtureResponse.isSuccessful && teamResponse.isSuccessful) {
                fixtures = fixtureResponse.body().orEmpty()
                teams = teamResponse.body().orEmpty()
            } else {
                error = "Failed to load data"
            }

        } catch (e: Exception) {
            error = e.message
        }

        isLoading = false
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {

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

        Text(
            text = "Upcoming Fixtures",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(visibleFixtures) { fixture ->
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFBBDEFB)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                    modifier = Modifier.fillMaxWidth()
                ){

                    Column(modifier = Modifier.padding(12.dp)) {

                        Surface(
                            shape = RoundedCornerShape(50),
                            color = Color(0xFF1976D2),
                            modifier = Modifier.padding(bottom = 8.dp)
                        ) {
                            Text(
                                text = "FIXTURE",
                                color = Color.White,
                                style = MaterialTheme.typography.labelSmall,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp)
                            )
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                fixture.homeTeam.name,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.weight(2f)
                            )

                            Text(
                                "VS",
                                style = MaterialTheme.typography.titleLarge,
                            )

                            Text(
                                fixture.awayTeam.name,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.weight(2f),
                                textAlign = TextAlign.End
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        val (date, time) = formatMatchDate(fixture.matchDate)
                        Text(
                            text = time + "    " + date,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Pitch: ${fixture.pitch.name}",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
            item {
                if (fixtures.size > 3 && !showAll) {
                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = { showAll = !showAll },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("View More")
                    }
                }
            }
        }
    }
}