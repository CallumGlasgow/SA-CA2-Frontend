package com.example.sa_ca2_frontend

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sa_ca2_frontend.ui.theme.SACA2FrontendTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SACA2FrontendTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Title(modifier: Modifier = Modifier) {
    Text(
        text = "6v6 League Planner",
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        style = MaterialTheme.typography.headlineMedium
    )
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    Text(
        text = "Welcome",
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        style = MaterialTheme.typography.headlineSmall
    )
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val teams = listOf("Team A", "Team B", "Team C", "Team D")
    Column(modifier = modifier.fillMaxSize()) {
        Title(modifier = Modifier.padding(top = 8.dp))
        Greeting(modifier = Modifier.padding(bottom = 8.dp))

        Spacer(modifier = Modifier.height(16.dp))
        Divider()
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
                Divider()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    SACA2FrontendTheme {
        MainScreen()
    }
}