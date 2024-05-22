package com.yusuf.components.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yusuf.components.ui.components.ResponsiveText
import com.yusuf.components.ui.util.ScaffoldWithAppBar


@Composable
fun ResponsiveTextScreen(onBackClick: () -> Unit = {}) {

    ScaffoldWithAppBar(title = "Responsive Text", onBackClick = onBackClick) {
        Box(
            Modifier
                .padding(8.dp)
                .width(120.dp)
        ) {
            ResponsiveText(text = "Long Text Long Text Long Text Long Text Long Text Long Text Long Text Long Text Long Text",
                color = MaterialTheme.colorScheme.onBackground,
                textStyle = MaterialTheme.typography.titleLarge,
                onTextSizeChanged = {})
        }
    }

}