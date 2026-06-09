package com.yusuf.components.ui.components.others

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntOffset
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun InstagramLikeContainer(
    modifier: Modifier = Modifier,
    heartReaction: @Composable () -> Unit,
    onDoubleTap: () -> Unit = {},
    content: @Composable BoxScope.() -> Unit
) {
    var showHeart by remember { mutableStateOf(false) }
    var containerHeight by remember { mutableIntStateOf(0) }

    // LaunchedEffect bu sayacı dinlediği için, sayı her değiştiğinde eski animasyonu  İPTAL EDECEK ve baştan başlayacak!
    var animationTriggerNonce by remember { mutableLongStateOf(0L) }

    val scale = remember { Animatable(0f) }
    val alpha = remember { Animatable(0f) }
    val translateY = remember { Animatable(0f) }
    val rotation = remember { Animatable(90f) }


    LaunchedEffect(showHeart, animationTriggerNonce, containerHeight) {
        if (showHeart && containerHeight > 0) {

            // 1. ADIM: TAM SIFIRLAMA (Hard Reset)
            // Eski animasyonlar iptal
            scale.snapTo(0f) // 0f -> 1.2f -> 300ms
            alpha.snapTo(0f) // 0f -> 1f -> 200ms
            rotation.snapTo(90f)
            translateY.snapTo(containerHeight / 2f) // Alignment center Reactionu tam ortaya koyuyordu
            // en alta çekmek için containerHeight/2 kadar aşağı kaydırıyoruz.

            // 2. ADIM: ANİMASYONU TEKRAR BAŞLATMA
            coroutineScope { // aynı anda başlaması için coroutineScope içinde 4 animasyonu da launch ediyoruz
                launch { alpha.animateTo(1f, tween(200)) }
                launch { scale.animateTo(1.2f, tween(300, easing = FastOutSlowInEasing)) }
                launch { translateY.animateTo(0f, tween(300, easing = FastOutSlowInEasing)) }
                launch { rotation.animateTo(0f, tween(300, easing = FastOutSlowInEasing)) }
            }

            scale.animateTo(1f, tween(100))// 1.2f -> 1f -> 100ms (Küçülme efekti)
            delay(500)

            alpha.animateTo(0f, tween(200)) // 1f -> 0f -> 200ms (Yavaşça kaybolma)
            showHeart = false
        }
    }

    Box(
        modifier = modifier
            .onSizeChanged { containerHeight = it.height }
            .pointerInput(Unit) {
                detectTapGestures(
                    onDoubleTap = {
                        // Çift tıklandığı an sayacı artırıyoruz.
                        // Bu hamle LaunchedEffect'i "Kapa-Aç" yaparak tüm animasyonu resetler!
                        animationTriggerNonce++
                        showHeart = true
                        onDoubleTap()
                    }
                )
            }
    ) {
        content()

        if (showHeart && containerHeight > 0) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .graphicsLayer {
                        scaleX = scale.value
                        scaleY = scale.value
                        this.alpha = alpha.value
                        rotationZ = rotation.value
                    }
                    .offset { IntOffset(0, translateY.value.toInt()) },
                contentAlignment = Alignment.Center
            ) {
                heartReaction()
            }
        }
    }
}