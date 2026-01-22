package com.photovault.ui.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun SettingsScreen(navController: NavController) {
    val autoBackup = remember { mutableStateOf(true) }
    val encryptionEnabled = remember { mutableStateOf(true) }
    val biometricEnabled = remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Settings")

        Text("Auto Backup", modifier = Modifier.padding(8.dp))
        Switch(
            checked = autoBackup.value,
            onCheckedChange = { autoBackup.value = it },
            modifier = Modifier.padding(8.dp)
        )

        Text("Encryption Enabled", modifier = Modifier.padding(8.dp))
        Switch(
            checked = encryptionEnabled.value,
            onCheckedChange = { encryptionEnabled.value = it },
            modifier = Modifier.padding(8.dp)
        )

        Text("Biometric Lock", modifier = Modifier.padding(8.dp))
        Switch(
            checked = biometricEnabled.value,
            onCheckedChange = { biometricEnabled.value = it },
            modifier = Modifier.padding(8.dp)
        )

        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Back")
        }
    }
}
