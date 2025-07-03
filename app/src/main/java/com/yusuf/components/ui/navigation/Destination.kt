package com.yusuf.components.ui.navigation

import androidx.compose.runtime.Composable

interface Destination {
    val route: String          // navController.navigate(route)
    val label: String          // Ana menüde görünen metin
    @Composable
    fun Content(onBack: () -> Unit)
}
