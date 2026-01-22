package com.photovault.ui.screens.share

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ShareScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Offline Sharing")
        Text("Share files via QR Code, Bluetooth, Xender, or Local Links")
        
        Button(
            onClick = { /* Generate QR */ },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Generate QR Code")
        }

        Button(
            onClick = { /* Share via Bluetooth */ },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Share via Bluetooth")
        }

        Button(
            onClick = { /* Share via Xender */ },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Share via Xender")
        }

        Button(
            onClick = { /* Generate link */ },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Generate Share Link")
        }

        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Back")
        }
    }
}
