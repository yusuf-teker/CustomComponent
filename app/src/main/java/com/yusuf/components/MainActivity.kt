package com.yusuf.components

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.yusuf.components.ui.screens.LoadingButtonScreen
import com.yusuf.components.ui.screens.MainScreen
import com.yusuf.components.ui.screens.ResponsiveTextScreen
import com.yusuf.components.ui.screens.ScrollingTextScreen
import com.yusuf.components.ui.screens.SearchBarScreen
import com.yusuf.components.ui.screens.TabbedScreenScreen
import kotlinx.serialization.Serializable

@Serializable
object ScreenResponsiveText

@Serializable
data class ScreenScrollingText(
    val text: String
)

@Serializable
object ScreenMain

@Serializable
object ScreenTabbedScreen

@Serializable
object ScreenSearchBar

@Serializable
object ScreenLoadingButton


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = ScreenMain
            ){
                composable<ScreenMain>{
                    MainScreen(
                        onNavigateToResponsiveText = {
                            navController.navigate(ScreenResponsiveText)
                        },
                        onNavigateToScrollingText = {
                            navController.navigate(ScreenScrollingText(it))
                        },
                        onNavigateToTabbedScreen = {
                            navController.navigate(ScreenTabbedScreen)
                        },
                        onNavigateToSearchBox = {
                            navController.navigate(ScreenSearchBar)
                        },
                        onNavigateToLoadingButton = {
                            navController.navigate(ScreenLoadingButton)
                        }
                    )
                }
                composable<ScreenResponsiveText>{
                    ResponsiveTextScreen(onBackClick = {navController.popBackStack()})
                }
                composable<ScreenScrollingText>{
                    val args = it.toRoute<ScreenScrollingText>()
                    ScrollingTextScreen(args.text, onBackClick = {navController.popBackStack()})
                }
                composable<ScreenTabbedScreen>{
                    TabbedScreenScreen(onBackClick = {navController.popBackStack()})
                }
                composable<ScreenSearchBar>{
                    SearchBarScreen { navController.popBackStack() }
                }
                composable<ScreenLoadingButton>{
                    LoadingButtonScreen{navController.popBackStack()}
                }

            }

        }

    }
}

