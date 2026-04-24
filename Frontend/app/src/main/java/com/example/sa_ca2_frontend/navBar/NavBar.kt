package com.example.sa_ca2_frontend.navBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.ui.res.stringResource
import com.example.sa_ca2_frontend.R

@Composable
fun BottomNavBar(
    // Icon list: https://fonts.google.com/icons
    selectedTab: Int,
    onTabSelected: (Int) -> Unit,
    containerColor: Color = Color.LightGray
) {
    NavigationBar (containerColor = containerColor) {
        NavigationBarItem(
            selected = selectedTab == 0,
            onClick = { onTabSelected(0) },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home"
                )
            },
            label = { Text(stringResource(id = R.string.navbarHome)) }

        )

        NavigationBarItem(
            selected = selectedTab == 1,
            onClick = { onTabSelected(1) },
            icon = {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.List,
                    contentDescription = "Teams leaderboard"
                )
            },
            label = { Text(stringResource(id = R.string.navbarLeaderboard)) }
        )

        NavigationBarItem(
            selected = selectedTab == 2,
            onClick = { onTabSelected(2) },
            icon = {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Fixtures"
                )
            },
            label = { Text(stringResource(id = R.string.nvabarFixture)) }
        )

        NavigationBarItem(
            selected = selectedTab == 3,
            onClick = { onTabSelected(3) },
            icon = {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Settings"
                )
            },
            label = { Text(stringResource(id = R.string.navbarSettings)) }
        )
    }
}