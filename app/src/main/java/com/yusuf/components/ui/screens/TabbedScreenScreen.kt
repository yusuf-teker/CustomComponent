package com.yusuf.components.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yusuf.components.ui.components.Screen
import com.yusuf.components.ui.components.TabbedScreen
import com.yusuf.components.ui.util.ScaffoldWithAppBar

@Composable
fun TabbedScreenScreen(onBackClick: () -> Unit = {}) {
    ScaffoldWithAppBar(
        title = "Tabbed Screen", onBackClick = onBackClick
    ) {

        val screen1 = Screen(title = "Tab 1") {
            Box(Modifier.padding(8.dp)) {
                Text(text = "Tab 1 Content")
            }
        }
        val screen2 = Screen(title = "Tab 2") {
            Box(Modifier.padding(8.dp)) {
                Text(text = "Tab 2 Content")
            }
        }

        Box(Modifier.padding(8.dp)) {
            TabbedScreen(
                initialPage = 0, onTabChanged = {
                    when (it) {
                        0 -> {}
                        1 -> {}
                    }
                }, screens = listOf(screen1, screen2)
            )
        }
    }

}