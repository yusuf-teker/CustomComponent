package com.yusuf.components

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yusuf.components.ui.navigation.allDestinations
import com.yusuf.components.ui.screens.MainScreen
import kotlinx.serialization.Serializable

@Serializable
object ScreenMain

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()


            NavHost(
                navController = navController,
                startDestination = ScreenMain
            ){
                composable<ScreenMain>{ // Base Ekran
                    MainScreen(
                        nav= navController,
                        dests=  allDestinations
                    )
                }
                allDestinations.forEach { dest -> // Child Ekranlar
                    composable(dest.route) {
                        dest.Content { navController.popBackStack() }
                    }
                }
            }

        }

    }
}
