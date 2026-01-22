package com.photovault.ui.navigation

sealed class NavDestination(val route: String) {
    object Home : NavDestination("home")
    object Browser : NavDestination("browser")
    object Editor : NavDestination("editor")
    object Audio : NavDestination("audio")
    object Video : NavDestination("video")
    object Vault : NavDestination("vault")
    object Share : NavDestination("share")
    object Settings : NavDestination("settings")
}
