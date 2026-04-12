package com.example.sa_ca2_frontend
import com.example.sa_ca2_frontend.navBar.BottomNavBar

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
import androidx.compose.runtime.* // dup

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SACA2FrontendTheme {
                var selectedTab by remember { mutableIntStateOf(0) }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomNavBar(
                            selectedTab = selectedTab,
                            onTabSelected = { index ->
                                selectedTab = index
                            }
                        )
                    }
                ) {innerPadding ->
                    when (selectedTab) {
                        0 -> MainScreen(modifier = Modifier.padding(innerPadding))
                        1 -> TeamsScreen(modifier = Modifier.padding(innerPadding))
                        2 -> SearchScreen(modifier = Modifier.padding(innerPadding))
                        3 -> SettingsScreen(modifier = Modifier.padding(innerPadding))
                    }
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

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    SACA2FrontendTheme {
        var selectedTab by remember { mutableIntStateOf(0) } // CHANGE PREVIEW SCREEN HERE!!

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = { BottomNavBar(selectedTab = selectedTab, onTabSelected = {selectedTab = it}) }
        ) { innerPadding ->
//            MainScreen(modifier = Modifier.padding(innerPadding))
            when (selectedTab) {
                0 -> MainScreen(modifier = Modifier.padding(innerPadding))
                1 -> TeamsScreen(modifier = Modifier.padding(innerPadding))
                2 -> SearchScreen(modifier = Modifier.padding(innerPadding))
                3 -> SettingsScreen(modifier = Modifier.padding(innerPadding))
            }
        }
    }
}

@Composable
fun TeamsScreen(modifier: Modifier = Modifier) {
    Text("Teams Screen", modifier = modifier.padding(16.dp))
}

@Composable
fun SearchScreen(modifier: Modifier = Modifier) {
    Text("Search Screen", modifier = modifier.padding(16.dp))
}

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    Text("Settings Screen", modifier = modifier.padding(16.dp))
}