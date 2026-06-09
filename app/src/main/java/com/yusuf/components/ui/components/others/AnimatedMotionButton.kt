package com.yusuf.components.ui.components.others

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun AnimatedMotionButton(
    text: String,
    onClick: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    // Butonun ölçeğini (scale) canlı olarak animatize etmek için Animatable kullanıyoruz
    val scale = remember { Animatable(1f) }

    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .wrapContentWidth()
            // 1. Canlı Ölçeklendirme: Kullanıcı bastıkça bu değer küçülecek/büyüyecek
            .scale(scale.value)
            // 2. Şekilli Tasarım: Düz mavi yerine canlı bir gradyan ve asimetrik kıvrım
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(Color(0xFF6200EE), Color(0xFF03DAC5))
                ),
                // %50 yerine modern, dinamik bir stadyum/elips şekli
                shape = RoundedCornerShape(topStart = 24.dp, bottomEnd = 24.dp, topEnd = 8.dp, bottomStart = 8.dp)
            )
            // 3. Pointer Input ile Canlı Etkileşim Yönetimi
            .pointerInput(Unit) {
                while (true) {
                    // Kullanıcı parmağını ekrana değdirdiği an (Down)
                    awaitPointerEventScope {
                        awaitFirstDown(requireUnconsumed = false)
                        coroutineScope.launch {
                            // Butonu jöle kıvamında %15 küçült
                            scale.animateTo(
                                targetValue = 0.85f,
                                animationSpec = spring(
                                    dampingRatio = Spring.DampingRatioMediumBouncy,
                                    stiffness = Spring.StiffnessLow
                                )
                            )
                        }

                        // Kullanıcı parmağını kaldırana veya iptal edene kadar bekle (Up)
                        val up = waitForUpOrCancellation()
                        if (up != null) {
                            // Eğer parmak butonun üzerinde kaldırıldıysa aksiyonu tetikle
                            onClick()
                        }

                        coroutineScope.launch {
                            // Parmağını çektiğinde yay efektiyle (Spring) eski boyutuna fırlasın
                            scale.animateTo(
                                targetValue = 1f,
                                animationSpec = spring(
                                    dampingRatio = Spring.DampingRatioHighBouncy, // Bol zıplamalı
                                    stiffness = Spring.StiffnessMedium
                                )
                            )
                        }
                    }
                }
            }
            .padding(horizontal = 32.dp, vertical = 16.dp), // Buton içi dolgu
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color.White,
            style = MaterialTheme.typography.titleMedium
        )
    }
}