package com.yusuf.components.ui.components.core

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.yusuf.components.ui.theme.CustomComponentTheme

/**
 * Production-ready Modern Bottom Sheet Component
 */
@Composable
fun ModernBottomSheet(
    isVisible: Boolean = false,
    onDismiss: () -> Unit,
    title: String = "",
    description: String = "",
    icon: ImageVector? = null,
    type: SheetType = SheetType.Info,
    primaryButton: String = "Tamam",
    secondaryButton: String? = null,
    onPrimaryButtonClick: () -> Unit = onDismiss,
    onSecondaryButtonClick: () -> Unit = onDismiss,
    dismissOnOutsideClick: Boolean = true,
    content: @Composable (() -> Unit)? = null
) {
    // Dışarıdan gelen isVisible değiştiğinde tetiklenir
    var isShowing by remember { mutableStateOf(isVisible) }
    LaunchedEffect(isVisible) {
        isShowing = isVisible
    }

    if (!isShowing) return

    Dialog(
        onDismissRequest = {
            if (dismissOnOutsideClick) {
                isShowing = false
                onDismiss()
            }
        },
        properties = DialogProperties(
            usePlatformDefaultWidth = false // Tam ekran kaplaması için şart
        )
    ) {
        // Karartılmış dış alan (Scrim)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Black.copy(alpha = 0.5f))
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    if (dismissOnOutsideClick) {
                        isShowing = false
                        onDismiss()
                    }
                },
            contentAlignment = Alignment.BottomCenter // Alt kısma yasla
        ) {
            // Tıklamaların Sheet içine sızmasını engellemek için boş tıklama dinleyicisi
            Box(modifier = Modifier.clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {}) {
                BottomSheetContent(
                    title = title,
                    description = description,
                    icon = icon,
                    type = type,
                    primaryButton = primaryButton,
                    secondaryButton = secondaryButton,
                    onDismiss = {
                        isShowing = false
                        onDismiss()
                    },
                    onPrimaryButtonClick = {
                        onPrimaryButtonClick()
                        isShowing = false
                        onDismiss()
                    },
                    onSecondaryButtonClick = {
                        onSecondaryButtonClick()
                        isShowing = false
                        onDismiss()
                    },
                    content = content
                )
            }
        }
    }
}

@Composable
private fun BottomSheetContent(
    title: String,
    description: String,
    icon: ImageVector?,
    type: SheetType,
    primaryButton: String,
    secondaryButton: String?,
    onDismiss: () -> Unit,
    onPrimaryButtonClick: () -> Unit,
    onSecondaryButtonClick: () -> Unit,
    content: @Composable (() -> Unit)?
) {
    val cornerRadius = 24.dp

    AnimatedVisibility(
        visible = true,
        enter = slideInVertically(
            initialOffsetY = { it },
            animationSpec = tween(durationMillis = 300)
        ) + fadeIn(animationSpec = tween(durationMillis = 300)),
        exit = slideOutVertically(
            targetOffsetY = { it },
            animationSpec = tween(durationMillis = 300)
        ) + fadeOut(animationSpec = tween(durationMillis = 300))
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = cornerRadius, topEnd = cornerRadius)),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            shape = RoundedCornerShape(topStart = cornerRadius, topEnd = cornerRadius)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)
            ) {
                // Üst Bar: Sürükleme Çubuğu ve Kapatma İkonu Yan Yana
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    // Merkezdeki Çizgi (Drag Handle)
                    Box(
                        modifier = Modifier
                            .width(40.dp)
                            .height(4.dp)
                            .clip(RoundedCornerShape(2.dp))
                            .background(type.color.copy(alpha = 0.4f))
                            .align(Alignment.Center)
                    )

                    // Sağ Üstteki Kapatma (X) Butonu
                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier.align(Alignment.TopEnd)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Kapat",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                // Ana İçerik Alanı
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, end = 24.dp, bottom = 32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // İkon
                    icon?.let {
                        Icon(
                            imageVector = it,
                            contentDescription = null,
                            modifier = Modifier.size(56.dp),
                            tint = type.color
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    // Başlık
                    if (title.isNotEmpty()) {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.headlineSmall.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            color = MaterialTheme.colorScheme.onSurface,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    // Açıklama
                    if (description.isNotEmpty()) {
                        Text(
                            text = description,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textAlign = TextAlign.Center,
                            lineHeight = MaterialTheme.typography.bodyMedium.lineHeight * 1.4
                        )
                    }

                    // Ekstra içerik eklenmişse buraya çizilir
                    content?.let {
                        Spacer(modifier = Modifier.height(16.dp))
                        it.invoke()
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Primary Button
                    Button(
                        onClick = onPrimaryButtonClick,
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = type.color,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(text = primaryButton, fontWeight = FontWeight.SemiBold)
                    }

                    // Secondary Button (Eğer metin verilmişse gösterilir)
                    secondaryButton?.let { btnText ->
                        Spacer(modifier = Modifier.height(12.dp))
                        OutlinedButton(
                            onClick = onSecondaryButtonClick,
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(text = btnText, color = MaterialTheme.colorScheme.onSurface)
                        }
                    }
                }
            }
        }
    }
}

/**
 * Alternatif Bottom Sheet (Daha hafif versiyon)
 */
@Composable
fun LightweightBottomSheet(
    isVisible: Boolean = false,
    onDismiss: () -> Unit,
    title: String,
    icon: ImageVector,
    actionButton: String = "Tamam",
    onActionClick: () -> Unit = onDismiss,
    dismissOnOutsideClick: Boolean = true
) {
    var isShowing by remember { mutableStateOf(isVisible) }
    LaunchedEffect(isVisible) {
        isShowing = isVisible
    }

    if (!isShowing) return

    Dialog(
        onDismissRequest = {
            if (dismissOnOutsideClick) {
                isShowing = false
                onDismiss()
            }
        },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Black.copy(alpha = 0.5f))
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    if (dismissOnOutsideClick) {
                        isShowing = false
                        onDismiss()
                    }
                },
            contentAlignment = Alignment.BottomCenter
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {}
                    .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, end = 24.dp, bottom = 32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Handle & Kapatma alanı
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .width(40.dp)
                                .height(4.dp)
                                .clip(RoundedCornerShape(2.dp))
                                .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f))
                                .align(Alignment.Center)
                        )
                        IconButton(
                            onClick = {
                                isShowing = false
                                onDismiss()
                            },
                            modifier = Modifier.align(Alignment.TopEnd)
                        ) {
                            Icon(imageVector = Icons.Default.Close, contentDescription = "Kapat")
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        modifier = Modifier.size(56.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = {
                            onActionClick()
                            isShowing = false
                            onDismiss()
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(text = actionButton)
                    }
                }
            }
        }
    }
}

sealed class SheetType {
    abstract val color: Color
    abstract val descriptionColor: Color

    data object Success : SheetType() {
        override val color: Color = Color(0xFF4CAF50)
        override val descriptionColor: Color = Color(0xFF2E7D32)
    }

    data object Error : SheetType() {
        override val color: Color = Color(0xFFF44336)
        override val descriptionColor: Color = Color(0xFFD32F2F)
    }

    data object Info : SheetType() {
        override val color: Color = Color(0xFF2196F3)
        override val descriptionColor: Color = Color(0xFF1565C0)
    }

    data object Warning : SheetType() {
        override val color: Color = Color(0xFFFF9800)
        override val descriptionColor: Color = Color(0xFFE65100)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewModernBottomSheetSuccess() {
    CustomComponentTheme {
        ModernBottomSheet(
            isVisible = true,
            onDismiss = {},
            title = "Başarılı İşlem",
            description = "Kaydınız başarıyla tamamlandı! Devam etmek için kaydedin.",
            icon = Icons.Outlined.CheckCircle,
            type = SheetType.Success,
            primaryButton = "Tamam",
            secondaryButton = "İptal"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewModernBottomSheetError() {
    CustomComponentTheme {
        ModernBottomSheet(
            isVisible = true,
            onDismiss = {},
            title = "Hata Oluştu",
            description = "Bağlantı hatası. Lütfen tekrar deneyin.",
            icon = Icons.Outlined.MoreVert,
            type = SheetType.Error,
            primaryButton = "Tekrar Dene",
            secondaryButton = "İptal"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLightweightBottomSheet2() {
    CustomComponentTheme {
        LightweightBottomSheet(
            isVisible = true,
            onDismiss = {},
            title = "Ayarlar Değiştirildi",
            icon = Icons.Default.Settings,
            actionButton = "Kaydet"
        )
    }
}