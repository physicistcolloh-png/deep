package com.photovault.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.photovault.ui.navigation.NavDestination

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("PhotoVault - Offline File & Photo Editor")
        
        Button(
            onClick = { navController.navigate(NavDestination.Browser.route) },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Browse Files")
        }

        Button(
            onClick = { navController.navigate(NavDestination.Audio.route) },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Audio Player")
        }

        Button(
            onClick = { navController.navigate(NavDestination.Video.route) },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Video Player")
        }

        Button(
            onClick = { navController.navigate(NavDestination.Vault.route) },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Security Vault")
        }

        Button(
            onClick = { navController.navigate(NavDestination.Share.route) },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Share Files")
        }

        Button(
            onClick = { navController.navigate(NavDestination.Settings.route) },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Settings")
        }
    }
}
