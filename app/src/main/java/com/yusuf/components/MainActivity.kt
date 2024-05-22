package com.yusuf.components

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yusuf.components.ui.components.ResponsiveText
import com.yusuf.components.ui.components.ScrollingText
import com.yusuf.components.ui.theme.CustomComponentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Main()
        }
    }
}

@Composable
fun Main() {
    CustomComponentTheme {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .height(40.dp)
                        .width(120.dp)
                ) {
                    ResponsiveText(text = "Long Text Long Text Long Text Long Text Long Text Long Text Long Text Long Text Long Text",
                        color = MaterialTheme.colorScheme.onBackground,
                        textStyle = MaterialTheme.typography.titleLarge,
                        onTextSizeChanged = {})
                }
                ScrollingText(
                    text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    Main()
}