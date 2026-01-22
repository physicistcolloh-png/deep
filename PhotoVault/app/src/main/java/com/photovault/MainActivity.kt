package com.photovault

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.photovault.ui.navigation.NavDestination
import com.photovault.ui.screens.home.HomeScreen
import com.photovault.ui.screens.browser.FileBrowserScreen
import com.photovault.ui.screens.editor.EditorScreen
import com.photovault.ui.screens.audio.AudioPlayerScreen
import com.photovault.ui.screens.video.VideoPlayerScreen
import com.photovault.ui.screens.vault.VaultScreen
import com.photovault.ui.screens.share.ShareScreen
import com.photovault.ui.screens.settings.SettingsScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PhotoVaultTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    PhotoVaultNavigation(navController)
                }
            }
        }
    }
}

@Composable
fun PhotoVaultTheme(content: @Composable () -> Unit) {
    MaterialTheme {
        content()
    }
}

@Composable
fun PhotoVaultNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavDestination.Home.route
    ) {
        composable(NavDestination.Home.route) {
            HomeScreen(navController)
        }
        composable(NavDestination.Browser.route) {
            FileBrowserScreen(navController)
        }
        composable("${NavDestination.Editor.route}/{photoId}") { backStackEntry ->
            EditorScreen(navController, backStackEntry.arguments?.getString("photoId") ?: "")
        }
        composable(NavDestination.Audio.route) {
            AudioPlayerScreen(navController)
        }
        composable(NavDestination.Video.route) {
            VideoPlayerScreen(navController)
        }
        composable(NavDestination.Vault.route) {
            VaultScreen(navController)
        }
        composable(NavDestination.Share.route) {
            ShareScreen(navController)
        }
        composable(NavDestination.Settings.route) {
            SettingsScreen(navController)
        }
    }
}
