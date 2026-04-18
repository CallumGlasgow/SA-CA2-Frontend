package com.example.sa_ca2_frontend.leagueTable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

data class TeamStanding(
    val name: String,
    val played: Int,
    val wins: Int,
    val draws: Int,
    val losses: Int,
    val points: Int
)

@Composable
fun LeagueTableScreen(modifier: Modifier = Modifier) {

    val teams = listOf(
        TeamStanding("Arsenal", 10, 8, 1, 1, 25),
        TeamStanding("Wolves", 10, 7, 2, 1, 23),
        TeamStanding("Man City", 10, 6, 2, 2, 20),
        TeamStanding("Liverpool", 10, 5, 2, 3, 17),
        TeamStanding("Chelsea", 10, 4, 1, 5, 13),
        TeamStanding("Yanited", 10, 3, 2, 5, 11),
        TeamStanding("Spurs", 10, 2, 1, 7, 7),
        TeamStanding("Brighton", 10, 1, 0, 9, 3)
    )

    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {

        Text(
            text = "League Table",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))

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
            itemsIndexed(teams) { index, team ->

                val bgColor = when (index) {
                    in 0..2 -> Color(0xFFDFF5E1) // green for top 3 (trophys?)
                    in 5..7 -> Color(0xFFF8D7DA) // red for bottom 3 (relegation? or loser penalty?)
                    else -> Color.Transparent
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(bgColor)
                        .padding(vertical = 10.dp)
                ) {
                    Text(team.name, modifier = Modifier.weight(2f))
                    Text("${team.played}", modifier = Modifier.weight(1f))
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