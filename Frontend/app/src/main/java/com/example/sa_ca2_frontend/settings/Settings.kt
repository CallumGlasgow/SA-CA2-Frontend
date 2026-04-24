package com.example.sa_ca2_frontend.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.sa_ca2_frontend.Model.User
import com.example.sa_ca2_frontend.R

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    onNavigateToRegister: () -> Unit = {},
    onNavigateToLogin: () -> Unit = {},
    onNavigateToCreateTeam: () -> Unit = {},
    onLogout: () -> Unit = {},
    isLoggedIn: Boolean,
    user: User,
    ) {

    val LoginText = stringResource(id = R.string.SettingsLoggedInAs, user.email)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = stringResource(id = R.string.SettingsTitle),
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        if (isLoggedIn) {
            Text(
                text = LoginText,
                style = MaterialTheme.typography.bodyMedium,
            )
            HorizontalDivider()

            Text(
                text = stringResource(id = R.string.SettingsRegisterTeam),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onNavigateToCreateTeam() }
                    .padding(16.dp),
                style = MaterialTheme.typography.bodyLarge
            )

            HorizontalDivider()

            Text(
                text = stringResource(id = R.string.SettingsLogout),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onLogout() }
                    .padding(16.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        } else {
            Text(
            text = stringResource(id = R.string.SettingsSignup),
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onNavigateToRegister() }
                .padding(16.dp),
            style = MaterialTheme.typography.bodyLarge
        )

        HorizontalDivider()

        Text(
            text = stringResource(id = R.string.SettingsLogin),
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onNavigateToLogin() }
                .padding(16.dp),
            style = MaterialTheme.typography.bodyLarge
        )

        }
    }
}