import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

sealed interface StoryState {
    data object Unseen : StoryState
    data object Loading : StoryState
    data object Seen : StoryState
}

private val instagramGradientColors = listOf(
    Color(0xFFFEDA75),
    Color(0xFFFA7E1E),
    Color(0xFFD62976),
    Color(0xFF962FBF),
    Color(0xFF4F5BD5),
    Color(0xFFFEDA75)
)

@Composable
fun InstagramStoryAvatar(
    state: StoryState,
    imageUrl: String,
    modifier: Modifier = Modifier,
    size: Dp = 72.dp,
    onClick: () -> Unit = {}
) {
    val density = LocalDensity.current

    val infiniteTransition = rememberInfiniteTransition(label = "story_loading")
    val loadingAngle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "loading_angle"
    )

    Box(
        modifier = modifier
            .size(size)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val totalPx = this.size.minDimension

            // Gerçek Instagram px oranları (72dp baz)
            val strokePx    = totalPx * 0.034f   // ~2.5dp @ 72dp — renkli halka
            val separatorPx = totalPx * 0.042f   // ~3dp   @ 72dp — beyaz boşluk
            val avatarPx    = totalPx - 2 * (strokePx + separatorPx)

            val ringRadius  = (totalPx - strokePx) / 2f
            val whiteRadius = avatarPx / 2f + separatorPx
            val avatarRadius= avatarPx / 2f

            // 1) Renkli / gri dış halka
            when (state) {
                StoryState.Unseen -> {
                    drawCircle(
                        brush = Brush.sweepGradient(instagramGradientColors, center = center),
                        radius = ringRadius,
                        style = Stroke(width = strokePx, cap = StrokeCap.Round)
                    )
                }
                StoryState.Loading -> {
                    drawCircle(
                        color = Color(0xFFDBDBDB),
                        radius = ringRadius,
                        style = Stroke(width = strokePx)
                    )
                    // drawArc bounding box'unu açıkça ver — aksi hâlde canvas dikdörtgense elips çizer
                    val arcSize = androidx.compose.ui.geometry.Size(ringRadius * 2, ringRadius * 2)
                    val arcOffset = androidx.compose.ui.geometry.Offset(
                        x = center.x - ringRadius,
                        y = center.y - ringRadius
                    )
                    drawArc(
                        brush = Brush.sweepGradient(instagramGradientColors, center = center),
                        startAngle = loadingAngle - 90f,
                        sweepAngle = 110f,
                        useCenter = false,
                        topLeft = arcOffset,
                        size = arcSize,
                        style = Stroke(width = strokePx, cap = StrokeCap.Round)
                    )
                }
                StoryState.Seen -> {
                    drawCircle(
                        color = Color(0xFFBDBDBD),
                        radius = ringRadius,
                        style = Stroke(
                            width = strokePx * 0.8f,
                            pathEffect = PathEffect.dashPathEffect(
                                intervals = floatArrayOf(strokePx * 1.6f, strokePx * 0.9f),
                                phase = 0f
                            )
                        )
                    )
                }
            }

            // 2) Beyaz separator halkası
            drawCircle(
                color = Color.White,
                radius = whiteRadius,
                center = center
            )

            // 3) Avatar arka planı (Coil kullanmıyorsan placeholder)
            drawCircle(
                color = Color(0xFF3A3A3A),
                radius = avatarRadius,
                center = center
            )
        }

        // Metin / AsyncImage overlay — Canvas'taki avatar dairesinin üstüne gelir
        // Avatar boyutunu px→dp çevirerek Box'a ver
        val avatarDp = with(density) {
            val totalPx = with(density) { size.toPx() }
            val strokePx    = totalPx * 0.034f
            val separatorPx = totalPx * 0.042f
            val avatarPx    = totalPx - 2 * (strokePx + separatorPx)
            avatarPx.toDp()
        }

        Box(
            modifier = Modifier
                .size(avatarDp)
                .clip(CircleShape),
            contentAlignment = Alignment.Center
        ) {
            // Gerçek kullanımda buraya AsyncImage(model = imageUrl, ...) koy
            Text(
                text = "Y",
                color = Color.White,
                fontSize = (avatarDp.value * 0.38f).sp
            )
        }
    }
}