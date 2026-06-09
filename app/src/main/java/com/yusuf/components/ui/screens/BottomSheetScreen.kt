package com.yusuf.components.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.yusuf.components.ui.components.core.ModernBottomSheet
import com.yusuf.components.ui.components.core.LightweightBottomSheet
import com.yusuf.components.ui.components.core.SheetType
import com.yusuf.components.ui.theme.CustomComponentTheme

/**
 * Modern Bottom Sheet Demo Ekranı
 *
 * Bu ekran ModernBottomSheet ve LightweightBottomSheet bileşenlerinin
 * farklı senaryolarda kullanımını gösterir.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetScreen( onBack: () -> Unit) {
    var isSuccessSheetVisible by remember { mutableStateOf(false) }
    var isErrorSheetVisible by remember { mutableStateOf(false) }
    var isInfoSheetVisible by remember { mutableStateOf(false) }
    var isWarningSheetVisible by remember { mutableStateOf(false) }
    var isLightweightVisible by remember { mutableStateOf(false) }
    var isFormVisible by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Modern Bottom Sheet") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Geri")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = contentColorFor(MaterialTheme.colorScheme.primary),
                    navigationIconContentColor = contentColorFor(MaterialTheme.colorScheme.primary)
                )
            )
        }
    ) { paddingValues ->

        val scrollState = androidx.compose.foundation.rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(scrollState)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Modern Bottom Sheet Örnekleri",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Aşağıdaki butonlara tıklayarak farklı Bottom Sheet görünümlerini görüntüleyin.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Success Type
            Button(
                onClick = { isSuccessSheetVisible = true },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4CAF50)
                ),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
            ) {
                Text("Success Görünümü")
            }

            // Error Type
            Button(
                onClick = { isErrorSheetVisible = true },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFF44336)
                ),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
            ) {
                Text("Error Görünümü")
            }

            // Info Type
            Button(
                onClick = { isInfoSheetVisible = true },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2196F3)
                ),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
            ) {
                Text("Info Görünümü")
            }

            // Warning Type
            Button(
                onClick = { isWarningSheetVisible = true },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF9800)
                ),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
            ) {
                Text("Warning Görünümü")
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Lightweight Version
            Button(
                onClick = { isLightweightVisible = true },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
            ) {
                Text("Lightweight Bottom Sheet")
            }

            // Form Version
            Button(
                onClick = { isFormVisible = true },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                ),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
            ) {
                Text("Form Girişi")
            }

            // Demo Cards
            Spacer(modifier = Modifier.height(24.dp))



            Spacer(modifier = Modifier.height(8.dp))

            ProfileCard(
                icon = Icons.Default.AccountCircle,
                title = "Profil Ayarları",
                description = "Hesap bilgilerinizi düzenleyin",
                onClick = { isLightweightVisible = true }
            )

            EmailCard(
                icon = Icons.Default.Email,
                title = "E-posta Doğrula",
                description = "E-posta adresinizi doğrulayın",
                onClick = { isInfoSheetVisible = true }
            )

            PhoneCard(
                icon = Icons.Default.Phone,
                title = "Telefon Numarası",
                description = "Telefon numaranızı güncelleyin",
                onClick = { isWarningSheetVisible = true }
            )

            FavoriteCard(
                icon = Icons.Default.Favorite,
                title = "Favoriler",
                description = "Beğenilen içerikleri görüntüleyin",
                onClick = { isLightweightVisible = true }
            )

            LockCard(
                icon = Icons.Default.Lock,
                title = "Şifre Güncelle",
                description = "Şifrenizi değiştirin",
                onClick = { isErrorSheetVisible = true }
            )
        }

        // Modern Bottom Sheets
        ModernBottomSheet(
            isVisible = isSuccessSheetVisible,
            onDismiss = { isSuccessSheetVisible = false },
            title = "Başarılı İşlem",
            description = "Kaydınız başarıyla tamamlandı! Devam etmek için kaydedin.",
            icon = Icons.Outlined.CheckCircle,
            type = SheetType.Success,
            primaryButton = "Tamam",
            secondaryButton = "İptal"
        )

        ModernBottomSheet(
            isVisible = isErrorSheetVisible,
            onDismiss = { isErrorSheetVisible = false },
            title = "Hata Oluştu",
            description = "Bağlantı hatası. Lütfen tekrar deneyin.",
            icon = Icons.Outlined.Warning,
            type = SheetType.Error,
            primaryButton = "Tekrar Dene",
            secondaryButton = "İptal"
        )

        ModernBottomSheet(
            isVisible = isInfoSheetVisible,
            onDismiss = { isInfoSheetVisible = false },
            title = "Bilgi",
            description = "Bu işlem hakkında bilgilendirme. Daha fazla detay için iletişime geçin.",
            icon = Icons.Outlined.Info,
            type = SheetType.Info,
            primaryButton = "Anladım",
            secondaryButton = "Daha Fazla"
        )

        ModernBottomSheet(
            isVisible = isWarningSheetVisible,
            onDismiss = { isWarningSheetVisible = false },
            title = "Dikkat",
            description = "Bu işlem geri alınamaz. Emin misiniz?",
            icon = Icons.Outlined.Warning,
            type = SheetType.Warning,
            primaryButton = "Devam Et",
            secondaryButton = "İptal"
        )

        // Lightweight Bottom Sheet
        LightweightBottomSheet(
            isVisible = isLightweightVisible,
            onDismiss = { isLightweightVisible = false },
            title = "Ayarlar",
            icon = Icons.Default.Settings,
            actionButton = "Kaydet"
        )

        // Form Input Version
        ModernBottomSheet(
            isVisible = isFormVisible,
            onDismiss = { isFormVisible = false },
            title = "Bilgileri Düzenle",
            description = "Aşağıdaki bilgileri girin ve kaydedin:",
            icon = Icons.Default.Settings,
            type = SheetType.Info,
            primaryButton = "Kaydet",
            secondaryButton = "İptal"
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(8.dp))

                TextInputDemo("Adınız", "Örn: Ahmet Yılmaz")
                Spacer(modifier = Modifier.height(8.dp))
                TextInputDemo("E-posta", "ornek@email.com")
                Spacer(modifier = Modifier.height(8.dp))
                TextInputDemo("Telefon", "0555 555 55 55")
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

/**
 * Profil kartı
 */
@Composable
fun ProfileCard(
    icon: ImageVector,
    title: String,
    description: String,
    onClick: () -> Unit
) {
    androidx.compose.foundation.layout.Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.surfaceVariant,
                shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
            )
            .padding(16.dp)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(32.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(20.dp)
        )
    }
}

/**
 * E-posta kartı
 */
@Composable
fun EmailCard(
    icon: ImageVector,
    title: String,
    description: String,
    onClick: () -> Unit
) {
    androidx.compose.foundation.layout.Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.surfaceVariant,
                shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
            )
            .padding(16.dp)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(32.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(20.dp)
        )
    }
}

/**
 * Telefon kartı
 */
@Composable
fun PhoneCard(
    icon: ImageVector,
    title: String,
    description: String,
    onClick: () -> Unit
) {
    androidx.compose.foundation.layout.Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.surfaceVariant,
                shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
            )
            .padding(16.dp)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(32.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(20.dp)
        )
    }
}

/**
 * Favori kartı
 */
@Composable
fun FavoriteCard(
    icon: ImageVector,
    title: String,
    description: String,
    onClick: () -> Unit
) {
    androidx.compose.foundation.layout.Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.surfaceVariant,
                shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
            )
            .padding(16.dp)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(32.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(20.dp)
        )
    }
}

/**
 * Şifre kartı
 */
@Composable
fun LockCard(
    icon: ImageVector,
    title: String,
    description: String,
    onClick: () -> Unit
) {
    androidx.compose.foundation.layout.Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.surfaceVariant,
                shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
            )
            .padding(16.dp)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(32.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(20.dp)
        )
    }
}

/**
 * Demo TextInput
 */
@Composable
fun TextInputDemo(label: String, placeholder: String) {
    androidx.compose.foundation.layout.Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        androidx.compose.material3.OutlinedTextField(
            value = "",
            onValueChange = {},
            placeholder = { Text(placeholder) },
            modifier = Modifier.fillMaxWidth(),
            enabled = false
        )
    }
}
