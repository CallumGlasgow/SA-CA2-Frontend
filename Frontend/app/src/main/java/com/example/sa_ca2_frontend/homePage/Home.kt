package com.example.sa_ca2_frontend.homePage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sa_ca2_frontend.Greeting
import com.example.sa_ca2_frontend.Title

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val teams = listOf("Team A", "Team B", "Team C", "Team D")
    Column(modifier = modifier.fillMaxSize()) {
        Title(modifier = Modifier.padding(top = 8.dp))
        Greeting(modifier = Modifier.padding(bottom = 8.dp))

        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(16.dp))

        // Team list
        LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
            items(teams) { team ->
                Text(
                    text = team,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
                HorizontalDivider()
            }
        }
    }
}