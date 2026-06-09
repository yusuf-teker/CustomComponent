package com.yusuf.components.ui.components.others

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NotificationBadge(
    count: Int,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
    ) {

        content()

        if (count > 0) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(
                        x = 6.dp,
                        y = (-6).dp
                    )
                    .clip(CircleShape)
                    .background(Color.Red)
                    .defaultMinSize(minWidth = 16.dp, minHeight = 16.dp)
                    .padding(horizontal = 4.dp, vertical = 2.dp),
                contentAlignment = Alignment.Center
            ) {
                AnimatedContent(
                    targetState = if (count > 99) "99+" else count.toString(),
                    transitionSpec = {
                        slideInVertically { height -> height } + fadeIn() togetherWith
                                slideOutVertically { height -> -height } + fadeOut() using
                                SizeTransform(clip = false)
                    },
                    label = "badge_animation"
                ) { text ->
                    Text(
                        text = text,
                        color = Color.White,
                        fontSize = 9.sp, // Yazıyı biraz daha kompakt yaptık
                        maxLines = 1
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun NotificationBadgePreview() {
    Box(
        modifier = Modifier.padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        NotificationBadge(count = 5) {
            Text("Inbox")
        }
    }
}