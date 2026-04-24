package com.example.sa_ca2_frontend.homePage

import PodiumBlock
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.unit.dp

import com.example.sa_ca2_frontend.Model.TeamResponse
import com.example.sa_ca2_frontend.Model.User
import com.example.sa_ca2_frontend.leagueTable.LeaderboardApiService
import com.example.sa_ca2_frontend.upcomingFixtures.FixtureApiService
import com.example.sa_ca2_frontend.upcomingFixtures.FixtureResponse
import com.example.sa_ca2_frontend.upcomingFixtures.formatMatchDate
import kotlin.collections.orEmpty

val gold = Color(0xFFFFD700)
val silver = Color(0xFFC0C0C0)
val bronze = Color(0xFFCD7F32)
val podiumHeights = listOf(100.dp, 120.dp, 80.dp) // 2nd, 1st, 3rd for podium

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    user: User
) {
    var fixtures by remember { mutableStateOf<List<FixtureResponse>>(emptyList()) }
    var isLoadingFixture by remember { mutableStateOf(true) }
    var errorFixture by remember { mutableStateOf<String?>(null) }
    var showAll by remember { mutableStateOf(false) }
    val visibleFixtures = if (showAll) fixtures else fixtures.take(1)

    var teams by remember { mutableStateOf<List<TeamResponse>>(emptyList()) }

    var isLoadingTeams by remember { mutableStateOf(true) }
    var isErrorTeams by remember { mutableStateOf<String?>(null) }


    LaunchedEffect(Unit) {
        try {
            val getFixtures = FixtureApiService.api.generateFixtures(10)
            val teamResponse = LeaderboardApiService.api.getTeams()

            if (getFixtures.isSuccessful && teamResponse.isSuccessful) {
                val fixtureResponse = FixtureApiService.api.getFixtures()
                if (fixtureResponse.isSuccessful) {
                    fixtures = fixtureResponse.body().orEmpty()
                } else {
                    errorFixture = "Could not fetch new fixtures"
                }
            } else {
                errorFixture = "Could not generate fixtures or load teams"
            }

        } catch (e: Exception) {
            errorFixture = e.message
        }
        isLoadingFixture = false

        try {
            isLoadingTeams = true
            val response = LeaderboardApiService.api.getTeams()

            if (response.isSuccessful) {
                teams = response.body() ?: emptyList()
            } else {
                isErrorTeams = "Failed to load teams"
            }

        } catch (e: Exception) {
            isErrorTeams = e.message
        }

        isLoadingTeams = false
    }

    val topTeams = teams.sortedByDescending { it.points }.take(3)

    Column(modifier = modifier.fillMaxSize()) {
        Text(
            text = "Peamount 6v6 League",
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = if (user.email.isNotBlank()) "Welcome ${user.email}" else "Welcome Guest",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text= "Next Match, Dont miss out!",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))

        if (isLoadingFixture) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
            Spacer(modifier = Modifier.height(2.dp))
        } else if (errorFixture != null) {
            Text("Error: $errorFixture")
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(visibleFixtures) { fixture ->
                    Card(
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFBBDEFB)),
                        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
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
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (isLoadingTeams) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
            Spacer(modifier = Modifier.height(2.dp))
        } else if (isErrorTeams != null) {
            Text("Error: $isErrorTeams")
        } else {
            Row(
                Modifier.fillMaxWidth().padding(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Bottom
            ) {
                // 2nd place
                if (topTeams.size > 1) {
                    PodiumBlock(
                        teamName = topTeams[1].name,
                        points = topTeams[1].points,
                        place = 2,
                        color = silver,
                        blockHeight = podiumHeights[0]
                    )
                }
                // 1st place
                if (topTeams.isNotEmpty()) {
                    PodiumBlock(
                        teamName = topTeams[0].name,
                        points = topTeams[0].points,
                        place = 1,
                        color = gold,
                        blockHeight = podiumHeights[1]
                    )
                }
                // 3rd place
                if (topTeams.size > 2) {
                    PodiumBlock(
                        teamName = topTeams[2].name,
                        points = topTeams[2].points,
                        place = 3,
                        color = bronze,
                        blockHeight = podiumHeights[2]
                    )
                }
            }
        }
    }
}