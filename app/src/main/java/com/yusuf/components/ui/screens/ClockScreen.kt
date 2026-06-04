package com.yusuf.components.ui.screens

import androidx.compose.runtime.Composable
import com.yusuf.components.ui.components.ClockBasic
import com.yusuf.components.ui.util.ScaffoldWithAppBar

@Composable
fun ClockScreen(onBack: () -> Unit) {

    ScaffoldWithAppBar(title = "Clock", onBackClick = onBack) {
            ClockBasic()
    }
}