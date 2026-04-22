package com.example.sa_ca2_frontend
import com.example.sa_ca2_frontend.navBar.BottomNavBar
import com.example.sa_ca2_frontend.auth.register.RegisterScreen
import com.example.sa_ca2_frontend.auth.login.LoginScreen
import com.example.sa_ca2_frontend.settings.SettingsScreen
import com.example.sa_ca2_frontend.leagueTable.LeagueTableScreen
import com.example.sa_ca2_frontend.upcomingFixtures.UpcomingFixturesScreen
import com.example.sa_ca2_frontend.homePage.HomeScreen
import com.example.sa_ca2_frontend.team.CreateTeamScreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.sa_ca2_frontend.ui.theme.SACA2FrontendTheme
import androidx.compose.runtime.* // dup#
import androidx.compose.runtime.mutableIntStateOf
import com.example.sa_ca2_frontend.Model.User

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SACA2FrontendTheme {
                var selectedTab by remember { mutableIntStateOf(0) }
                var user by remember { mutableStateOf(User(0, "")) }

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
                        0 -> HomeScreen(
                            modifier = Modifier.padding(innerPadding),
                            user = user
                        )
                        1 -> LeagueTableScreen(modifier = Modifier.padding(innerPadding))
                        2 -> UpcomingFixturesScreen(modifier = Modifier.padding(innerPadding))
                        3 -> SettingsScreen(
                            modifier = Modifier.padding(innerPadding),
                            onNavigateToRegister = { selectedTab = 4 },
                            onNavigateToLogin = { selectedTab = 5 },
                            onNavigateToCreateTeam = { selectedTab = 6 },
                            onLogout = {
                                user = User(0, "")
                            },
                            isLoggedIn = user.id > 0,
                            user = user,

                        )
                        4 -> RegisterScreen(
                            modifier = Modifier.padding(innerPadding),
                            onRegisterSuccess = {
                                selectedTab = 5
                            }
                        )
                        5 -> LoginScreen(
                            modifier = Modifier.padding(innerPadding),
                            onLoginSuccess = { email, id ->
                                selectedTab = 0
                                user = User(id, email)
                            },
                        )
                        6 -> CreateTeamScreen(
                            modifier = Modifier.padding(innerPadding),
                            onCreateSuccess = {
                                selectedTab = 1
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    SACA2FrontendTheme {
        var selectedTab by remember { mutableIntStateOf(6) }

        var user by remember { mutableStateOf(User(1, "test@example.com")) }

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                BottomNavBar(
                    selectedTab = selectedTab,
                    onTabSelected = { selectedTab = it }
                )
            }
        ) { innerPadding ->
            when (selectedTab) {
                0 -> HomeScreen(
                    modifier = Modifier.padding(innerPadding),
                    user = user
                )
                1 -> LeagueTableScreen(modifier = Modifier.padding(innerPadding))
                2 -> UpcomingFixturesScreen(modifier = Modifier.padding(innerPadding))
                3 -> SettingsScreen(
                    modifier = Modifier.padding(innerPadding),
                    onNavigateToRegister = { selectedTab = 4 },
                    onNavigateToLogin = { selectedTab = 5 },
                    onNavigateToCreateTeam = { selectedTab = 6 },
                    onLogout = { user = User(0, "") },
                    isLoggedIn = user.id > 0,
                    user = user,
                )
                4 -> RegisterScreen(
                    modifier = Modifier.padding(innerPadding),
                    onRegisterSuccess = { selectedTab = 5 }
                )
                5 -> LoginScreen(
                    modifier = Modifier.padding(innerPadding),
                    onLoginSuccess = { email, id ->
                        selectedTab = 0
                        user = User(id, email)
                    }
                )
                6 -> CreateTeamScreen(
                    modifier = Modifier.padding(innerPadding),
                    onCreateSuccess = { selectedTab = 1 }
                )
            }
        }
    }
}