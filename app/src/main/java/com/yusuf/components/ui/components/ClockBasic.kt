package com.yusuf.components.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import kotlinx.coroutines.delay
import java.util.Calendar

@Composable
fun ClockBasic() {

    // 1. Saatin akıcı şekilde ilerlemesi için zaman state'i tutuyoruz
    val currentTime = remember { mutableStateOf(Calendar.getInstance()) }

    // Her saniye başı zamanı tetikleyen efekt
    LaunchedEffect(Unit) {
        while (true) {
            currentTime.value = Calendar.getInstance()
            delay(1000)
        }
    }

    val time = currentTime.value
    val currentHour = time.get(Calendar.HOUR)
    val currentMinute = time.get(Calendar.MINUTE)

    val radius = 100.dp
    val padding = radius / 4

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .border(width = 1.dp, shape = CircleShape, color = MaterialTheme.colorScheme.primary)
            .size(radius * 2f + padding) // Çap + padding
            .padding(padding)
    ) {

        // --- SAYILARIN ÇİZİLMESİ ---
        (1..12).forEach { hour ->
            val angle = Math.toRadians(90.0 - (hour * 30.0))
            val cosValue = Math.cos(angle).toFloat()
            val sinValue = Math.sin(angle).toFloat()

            val xOffset = cosValue * radius
            val yOffset = sinValue * radius

            Text(
                text = hour.toString(),
                color = Color.Black,
                modifier = Modifier.offset(xOffset, -yOffset)
            )
        }

        // --- AKREP VE YELKOVANIN ÇİZİLMESİ ---

        // Yelkovan Boyu ve Açısı (Dakika başına 6 derece hareket eder: 360/60)
        val minuteHandLength = radius * 0.8f
        val minuteAngle = Math.toRadians(90.0 - (currentMinute * 6.0))

        // Akrep Boyu ve Açısı (Saat başına 30 derece + dakikanın getirdiği küçük kayma payı)
        val hourHandLength = radius * 0.5f
        val hourAngle = Math.toRadians(90.0 - ((currentHour * 30.0) + (currentMinute * 0.5)))

        Canvas(modifier = Modifier.fillMaxSize()) {
            // Canvas içindeki merkez noktası (Box'ın tam ortası olur)
            val centerOffset = size.center

            // 1. Yelkovan Uç Noktası Hesaplama (Uzun Kol)
            val minuteX = (minuteHandLength.toPx() * Math.cos(minuteAngle)).toFloat()
            val minuteY = (minuteHandLength.toPx() * Math.sin(minuteAngle)).toFloat()

            // Yelkovan Çizgisi
            drawLine(
                color = Color.Black,
                start = centerOffset,
                end = Offset(centerOffset.x + minuteX, centerOffset.y - minuteY), // Y yine eksi (-)
                strokeWidth = 4.dp.toPx(),
                cap = StrokeCap.Round
            )

            // 2. Akrep Uç Noktası Hesaplama (Kısa Kol)
            val hourX = (hourHandLength.toPx() * Math.cos(hourAngle)).toFloat()
            val hourY = (hourHandLength.toPx() * Math.sin(hourAngle)).toFloat()

            // Akrep Çizgisi
            drawLine(
                color = Color.Gray,
                start = centerOffset,
                end = Offset(centerOffset.x + hourX, centerOffset.y - hourY), // Y yine eksi (-)
                strokeWidth = 7.dp.toPx(),
                cap = StrokeCap.Round
            )

            // 3. Merkezdeki Milleri Kapatan Küçük Göbek Noktası
            drawCircle(
                color = Color.Black,
                radius = 5.dp.toPx(),
                center = centerOffset
            )
        }
    }
}