package com.yusuf.components.ui.screens

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yusuf.components.ui.theme.CustomComponentTheme
import com.yusuf.components.ui.util.ScaffoldWithAppBar
import com.yusuf.components.ui.util.longText

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MainScreen(
    onNavigateToResponsiveText: () -> Unit = {},
    onNavigateToScrollingText: (text: String) -> Unit = {},
    onNavigateToTabbedScreen: () -> Unit = {},
    onNavigateToSearchBox: () -> Unit = {},
    onNavigateToLoadingButton: () -> Unit = {},
    onNavigateToAnimations: () -> Unit = {}
) {

    CustomComponentTheme {
        ScaffoldWithAppBar(title = "MainScreen", showBackIcon = false) {
            FlowRow(
                modifier = Modifier.fillMaxSize()
            ) {
                EllipseButton(text = "Responsive Text") {
                    onNavigateToResponsiveText()
                }
                EllipseButton(text = "Scrolling Text") {
                    onNavigateToScrollingText(longText)
                }
                EllipseButton(text = "Tabbed Screen") {
                    onNavigateToTabbedScreen()
                }
                EllipseButton(text = "SearchBox") {
                    onNavigateToSearchBox()
                }
                EllipseButton(text = "Loading Button") {
                    onNavigateToLoadingButton()
                }
                EllipseButton(text = "Animations") {
                    onNavigateToAnimations()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    MainScreen()
}


@Composable
fun EllipseButton(
    text: String, onClick: () -> Unit
) {
    Button(
        onClick = onClick, shape = RoundedCornerShape(50), colors = ButtonDefaults.buttonColors(
            containerColor = Color.Blue
        ), modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .wrapContentWidth()
    ) {
        Text(
            text = text, color = Color.White, style = MaterialTheme.typography.bodyMedium
        )
    }
}
