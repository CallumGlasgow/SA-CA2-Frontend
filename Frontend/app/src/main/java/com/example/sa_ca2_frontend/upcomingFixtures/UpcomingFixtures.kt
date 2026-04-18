package com.example.sa_ca2_frontend.upcomingFixtures

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

data class Fixture(
    val homeTeam: String,
    val awayTeam: String,
    val date: String,
    val time: String,
    val Pitch: String,
)

@Composable
fun UpcomingFixturesScreen(modifier: Modifier = Modifier) {

    val fixtures = listOf(
        Fixture("Arsenal", "Chelsea", "Fri 19 Apr", "18:00", "Pitch A"),
        Fixture("Man City", "Liverpool", "Sat 20 Apr", "14:00", "Pitch B"),
        Fixture("Wolves", "Spurs", "Sat 20 Apr", "16:00", "Pitch A"),
        Fixture("Brighton", "Yanited", "Sun 21 Apr", "15:00", "Pitch D"),
        Fixture("Chelsea", "Arsenal", "Fri 26 Apr", "18:00", "Pitch C"),
        Fixture("Liverpool", "Wolves", "Sat 27 Apr", "14:00", "Pitch B"),
        Fixture("Spurs", "Man City", "Sun 28 Apr", "16:00", "Pitch A")
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {

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
            items(fixtures) { fixture ->

                Card(
                    elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Column(modifier = Modifier.padding(12.dp)) {

                        // teams row
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = fixture.homeTeam,
                                fontWeight = FontWeight.SemiBold
                            )

                            Text(
                                text = "VS",
                                style = MaterialTheme.typography.labelLarge
                            )

                            Text(
                                text = fixture.awayTeam,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        // time row
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = fixture.date)
                            Text(text = fixture.time, fontWeight = FontWeight.Bold)
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = fixture.Pitch,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )

                    }
                }
            }
        }
    }
}