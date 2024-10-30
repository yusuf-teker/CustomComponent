package com.yusuf.components.ui.screens.animations

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yusuf.components.ui.components.DraggableLetters
import com.yusuf.components.ui.util.ScaffoldWithAppBar

@Composable
fun DraggableLettersScreen(
    onBackClick: () -> Unit = {}
){
    ScaffoldWithAppBar(title = "DraggableLettersScreen", onBackClick = onBackClick) {
        Box(
            Modifier.padding(8.dp)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            DraggableLetters()
        }
    }
}