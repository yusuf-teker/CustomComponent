package com.yusuf.components.ui.components.instagram

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yusuf.components.R
import com.yusuf.components.ui.theme.CustomComponentTheme

/**
 * Instagram Followers Screen Component
 * Realistic, professional Instagram takipçi ekranı
 */

data class InstagramUser(
    val id: String,
    val username: String,
    val fullName: String?,
    val avatarUrl: String?,
    val isFollowing: Boolean = false,
    val isMutual: Boolean = false,
    val isVerified: Boolean = false
)

enum class InstagramTab {
    Followers, Following, Blocked
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InstagramFollowersScreen(
    currentUser: InstagramUser,
    followers: List<InstagramUser>,
    following: List<InstagramUser>,
    blocked: List<InstagramUser>,
    onBackClick: () -> Unit = {},
    onFollowUser: (String) -> Unit = {},
    onBlockUser: (String) -> Unit = {},
    onMessageUser: (String) -> Unit = {},
    onMoreOptions: (InstagramUser) -> Unit = {}
) {
    var selectedTab by remember { mutableStateOf(InstagramTab.Followers) }
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = currentUser.username,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Geri"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface,
                    navigationIconContentColor = MaterialTheme.colorScheme.onSurface
                ),
                windowInsets = WindowInsets(0.dp)
            )
        },
        floatingActionButton = {
            InstagramFAB(
                icon = Icons.Default.Add,
                contentDescription = "İsteğe Gönder",
                onClick = { /* İşlev buraya gelecek */ }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
        ) {
            // Instagram Sekme Çubuğu
            InstagramTabBar(
                selectedTab = selectedTab,
                onTabChanged = {
                    selectedTab = it
                    searchQuery = "" // Sekme değiştiğinde aramayı sıfırla
                }
            )

            // Arama Çubuğu Bölümü
            Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                InstagramSearchBar(
                    query = searchQuery,
                    onQueryChange = { searchQuery = it }
                )
            }

            // Liste Alanı
            val isBlockedList = selectedTab == InstagramTab.Blocked
            val currentList = when (selectedTab) {
                InstagramTab.Followers -> followers
                InstagramTab.Following -> following
                InstagramTab.Blocked -> blocked
            }

            val filteredUsers = if (searchQuery.isEmpty()) {
                currentList
            } else {
                currentList.filter {
                    it.username.contains(searchQuery, ignoreCase = true) ||
                            (it.fullName?.contains(searchQuery, ignoreCase = true) == true)
                }
            }

            InstagramUserList(
                users = filteredUsers,
                isBlockedList = isBlockedList,
                onFollowUser = onFollowUser,
                onBlockUser = onBlockUser,
                onMessageUser = onMessageUser,
                onMoreOptions = onMoreOptions,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun InstagramUserList(
    users: List<InstagramUser>,
    isBlockedList: Boolean,
    onFollowUser: (String) -> Unit,
    onBlockUser: (String) -> Unit,
    onMessageUser: (String) -> Unit,
    onMoreOptions: (InstagramUser) -> Unit,
    modifier: Modifier = Modifier
) {
    if (users.isEmpty()) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(32.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.PersonAdd,
                    contentDescription = null,
                    modifier = Modifier.size(64.dp),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.3f)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Sonuç bulunamadı",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "Aradığınız kriterlere uygun kullanıcı bulunmamaktadır.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                    textAlign = TextAlign.Center
                )
            }
        }
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 4.dp),
            verticalArrangement = Arrangement.spacedBy(1.dp)
        ) {
            items(
                items = users,
                key = { it.id }
            ) { user ->
                InstagramUserRow(
                    user = user,
                    isBlockedList = isBlockedList,
                    onFollowUser = onFollowUser,
                    onBlockUser = onBlockUser,
                    onMessageUser = onMessageUser,
                    onMoreOptions = onMoreOptions
                )
            }
        }
    }
}

@Composable
private fun InstagramUserRow(
    user: InstagramUser,
    isBlockedList: Boolean,
    onFollowUser: (String) -> Unit,
    onBlockUser: (String) -> Unit,
    onMessageUser: (String) -> Unit,
    onMoreOptions: (InstagramUser) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surface
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Profil Resmi
            Box(modifier = Modifier.size(56.dp)) {
                Image(
                    painter = painterResource(R.drawable.avatar),
                    contentDescription = user.username,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.surfaceVariant),
                    contentScale = ContentScale.Crop
                )

                if (user.isVerified) {
                    Box(
                        modifier = Modifier
                            .size(18.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF0095F6))
                            .align(Alignment.BottomEnd)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Onaylı Hesap",
                            modifier = Modifier.size(12.dp).align(Alignment.Center),
                            tint = Color.White
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Kullanıcı Detayları
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = user.username,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                if (!user.fullName.isNullOrEmpty()) {
                    Text(
                        text = user.fullName,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Buton Kombinasyonları
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (isBlockedList) {
                    Button(
                        onClick = { onBlockUser(user.id) },
                        modifier = Modifier.height(32.dp),
                        contentPadding = PaddingValues(horizontal = 14.dp, vertical = 0.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0095F6))
                    ) {
                        Text("Engeli Kaldır", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
                    }
                } else if (user.isFollowing) {
                    OutlinedButton(
                        onClick = { onMessageUser(user.id) },
                        modifier = Modifier.height(32.dp),
                        contentPadding = PaddingValues(horizontal = 14.dp, vertical = 0.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.onSurface)
                    ) {
                        Text("Mesaj", fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                    }
                } else {
                    Button(
                        onClick = { onFollowUser(user.id) },
                        modifier = Modifier.height(32.dp),
                        contentPadding = PaddingValues(horizontal = 14.dp, vertical = 0.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0095F6))
                    ) {
                        Text(
                            text = if (user.isMutual) "Geri Takip Et" else "Takip Et",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                    }
                }

                IconButton(
                    onClick = { onMoreOptions(user) },
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "Daha Fazla Seçenek",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
private fun InstagramTabBar(
    selectedTab: InstagramTab,
    onTabChanged: (InstagramTab) -> Unit
) {
    val tabTitles = listOf("Takipçiler", "Takip Edilen", "Engellenen")
    val tabIcons = listOf(Icons.Default.People, Icons.Default.PersonAdd, Icons.Default.Lock)

    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 1.dp
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().height(48.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            tabTitles.forEachIndexed { index, title ->
                val isSelected = index == selectedTab.ordinal
                val targetTab = InstagramTab.entries[index]

                val contentColor = if (isSelected) {
                    MaterialTheme.colorScheme.onSurface
                } else {
                    MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
                }

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clickable { onTabChanged(targetTab) },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = tabIcons[index],
                        contentDescription = title,
                        tint = contentColor,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = title,
                        fontSize = 11.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                        color = contentColor
                    )
                }
            }
        }
    }
}

@Composable
private fun InstagramSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = query,
        onValueChange = onQueryChange,
        placeholder = { Text("Ara", style = MaterialTheme.typography.bodyMedium) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Ara",
                tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
            )
        },
        trailingIcon = {
            if (query.isNotEmpty()) {
                IconButton(onClick = { onQueryChange("") }) {
                    Icon(
                        imageVector = Icons.Outlined.Clear,
                        contentDescription = "Temizle",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        },
        modifier = modifier
            .fillMaxWidth()
            .height(44.dp),
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        interactionSource = remember { MutableInteractionSource() }
    )
}

@Composable
private fun InstagramFAB(
    icon: ImageVector,
    contentDescription: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier.size(56.dp),
        containerColor = Color(0xFF0095F6),
        contentColor = Color.White,
        shape = CircleShape,
        elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 4.dp)
    ) {
        Icon(imageVector = icon, contentDescription = contentDescription)
    }
}

// Preview Mock Verileri
val sampleFollowers = listOf(
    InstagramUser("1", "alperen_yilmaz", "Alperen Yılmaz", null, isFollowing = true, isMutual = true, isVerified = true),
    InstagramUser("2", "ayse_demir", "Ayşe Demir", null, isFollowing = false, isMutual = false),
    InstagramUser("3", "cem_ozkan", "Cem Özkan", null, isFollowing = true, isMutual = false)
)

val sampleFollowing = listOf(
    InstagramUser("9", "elif_tanriverdi", "Elif Tanrıverdi", null, isFollowing = true, isMutual = true)
)

val sampleBlocked = listOf(
    InstagramUser("12", "spam_user1", "Spam User 1", null, isFollowing = false, isMutual = false)
)

val sampleCurrentUser = InstagramUser("current", "yusuf_teker", "Yusuf Teker", null, isVerified = true)

@Preview(showBackground = true, widthDp = 375, heightDp = 667)
@Composable
fun InstagramFollowersScreenPreview() {
    CustomComponentTheme(darkTheme = false) {
        InstagramFollowersScreen(
            currentUser = sampleCurrentUser,
            followers = sampleFollowers,
            following = sampleFollowing,
            blocked = sampleBlocked
        )
    }
}