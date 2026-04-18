package com.example.sa_ca2_frontend.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    onNavigateToRegister: () -> Unit = {},
    onNavigateToLogin: () -> Unit = {},
    onNavigateToCreateTeam: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Settings",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Sign Up",
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onNavigateToRegister() }
                .padding(16.dp),
            style = MaterialTheme.typography.bodyLarge
        )

        HorizontalDivider()

        Text(
            text = "Login",
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onNavigateToLogin() }
                .padding(16.dp),
            style = MaterialTheme.typography.bodyLarge
        )

        HorizontalDivider()

        Text(
            text = "Register a Team",
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onNavigateToCreateTeam() }
                .padding(16.dp),
            style = MaterialTheme.typography.bodyLarge
        )

        HorizontalDivider()
    }
}