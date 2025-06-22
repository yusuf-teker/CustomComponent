package com.yusuf.components.ui.screens

import android.widget.Button
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.yusuf.components.ui.components.rainbowColors
import com.yusuf.components.ui.components.rgbBorder
import com.yusuf.components.ui.components.rgbBorderCmp
import com.yusuf.components.ui.util.ScaffoldWithAppBar

@Composable
fun RgbBackgroundScreen(onBackClick: () -> Unit = {}) {

    val neonGlowColors = listOf(
        Color(0xFFFF00FF), // magenta
        Color(0xFF00FFFF), // camgöbeği
        Color(0xFF39FF14), // neon yeşil
        Color(0xFFFFFF00), // sarı
        Color(0xFFFF00FF)  // tekrar magenta
    )
    ScaffoldWithAppBar(title = "Rgb Border", onBackClick = onBackClick) {

        Column {

                Text(modifier = Modifier.rgbBorder(
                    strokeWidth = 8.dp,
                    shape = RectangleShape,
                    duration = 5000,
                    colorList = neonGlowColors
                ).size(200.dp).padding(16.dp),
                    text = "RGB Border Example")

            Box(
                modifier = Modifier.size(200.dp)
                    .background(Color.Black)
                    .rgbBorder(strokeWidth = 8.dp, shape = CircleShape, duration = 5000),
                contentAlignment = Alignment.Center

            ){
                Text("Compose android", color = Color.Red)

            }

            Box(// COMPOSE MULTIPLATFORM
                modifier = Modifier.fillMaxSize()
                    .background(Color.Black)
                    .rgbBorderCmp(strokeWidth = 8.dp, shape = CircleShape, duration = 5000),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ){
                Text("Compose multiplatform", color = Color.Red)
            }


        }

    }

}