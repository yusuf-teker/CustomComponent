package com.yusuf.components.ui.screens


import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.yusuf.components.ui.components.LikeCircleWithBadge
import com.yusuf.components.ui.util.ScaffoldWithAppBar

@Composable
fun CustomIconsScreen(onBackClick: () -> Unit = {}) {

    ScaffoldWithAppBar(title = "Custom Icons", onBackClick = onBackClick) {
        Column {
            LikeCircleWithBadge()
        }
    }

}