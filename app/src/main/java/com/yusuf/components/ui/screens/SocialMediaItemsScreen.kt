package com.yusuf.components.ui.screens

import InstagramStoryAvatar
import StoryState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yusuf.components.R
import com.yusuf.components.ui.components.others.ExpandableCard
import com.yusuf.components.ui.components.others.InstagramLikeContainer
import com.yusuf.components.ui.components.others.NotificationBadge
import com.yusuf.components.ui.components.others.ReactionItem
import com.yusuf.components.ui.components.others.ReactionPicker
import com.yusuf.components.ui.components.others.TypingIndicator
import com.yusuf.components.ui.util.ScaffoldWithAppBar
import kotlinx.coroutines.delay
import kotlinx.serialization.Serializable

@Serializable
object ScreenSocialMediaItems // Base Ekran Tanımı

@Composable
fun SocialMediaItemsScreen(onBackClick: () -> Unit = {}) {
    val navController = rememberNavController()

    // İç navigasyon: Sosyal medya elemanlarının listesi ve detay ekranları arasında dönecek
    NavHost(
        navController = navController,
        startDestination = ScreenSocialMediaItems
    ) {
        composable<ScreenSocialMediaItems> { // Ana Liste Ekranı
            SocialMediaListScreen(
                nav = navController,
                onBackClick = onBackClick
            )
        }

        // Dinamik olarak tüm alt sosyal medya ekranlarını navigasyona kaydediyoruz
        /*allSocialMediaDestinations.forEach { dest ->
            composable(dest.route) {
                dest.Content { navController.popBackStack() }
            }
        }*/
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SocialMediaListScreen(
    nav: NavController,
    onBackClick: () -> Unit
) {
    ScaffoldWithAppBar(title = "Social Media Items", onBackClick = onBackClick) {

        var storyState by remember {
            mutableStateOf<StoryState>(
                StoryState.Unseen
            )
        }

        LaunchedEffect(storyState) {

            if (storyState == StoryState.Loading) {

                delay(2500)

                storyState = StoryState.Seen
            }
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
        ) {

            item {
                InstagramStoryAvatar(
                    size = 128.dp,
                    state = storyState,
                    imageUrl = "",
                    onClick = {

                        storyState = StoryState.Loading

                    }
                )
            }
            item {
                TypingIndicator()
            }
            item {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center // Hücrenin ortasına hizala
                ) {
                    NotificationBadge(
                        count = 15,
                        // ÇÖZÜM 1: Badge kutusunun ikonun boyutunu aşmamasını sağlıyoruz
                        modifier = Modifier.wrapContentSize()
                    ) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = null,
                            // ÇÖZÜM 2: İkonun boyutunu netleştiriyoruz
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }

            item {
                var expanded by remember {
                    mutableStateOf(false)
                }

                ExpandableCard(
                    expanded = expanded,
                    onExpandedChange = {
                        expanded = it
                    },

                    header = {
                        Text("Account Settings")
                    },

                    content = {
                        Text("Long settings content...")
                    }
                )
            }


            item {
                InstagramLikeContainer(

                    // 1. ÇİFT TIKLANINCA ORTAYA ÇIKACAK REACTION
                    heartReaction = {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(48.dp)
                        )
                    },

                    onDoubleTap = {

                    },

                    content = {
                        Image(
                            painter = painterResource(id = R.drawable.avatar),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                        )
                    }
                )
            }

            item {
                ReactionPicker(
                    reactions = listOf(
                        ReactionItem("like") { Text("👍", fontSize = 28.sp) },
                        ReactionItem("love") { Text("❤️", fontSize = 28.sp) },
                        ReactionItem("fire") { Text("🔥", fontSize = 28.sp) },
                        ReactionItem("wow") { Text("😮", fontSize = 28.sp) },
                        ReactionItem("rocket") { Text("🚀", fontSize = 28.sp) }
                    ),
                    onReactionSelected = { reaction ->

                        println(reaction.id)

                    }
                ) {

                    Surface(
                        shape = RoundedCornerShape(16.dp)
                    ) {

                        Text(
                            text = "Selam 👋",
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        }






    }
}
