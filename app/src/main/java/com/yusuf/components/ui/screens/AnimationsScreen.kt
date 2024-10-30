package com.yusuf.components.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yusuf.components.ScreenAnimations
import com.yusuf.components.ui.screens.animations.DraggableLettersScreen
import com.yusuf.components.ui.util.ScaffoldWithAppBar
import kotlinx.serialization.Serializable

@Serializable
object DraggableLettersScreen

@Composable
fun AnimationsScreen(onBackClick: () -> Unit = {}) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ScreenAnimations
    ){
        composable<ScreenAnimations>{
            Screen(
                onNavigateToAnimation1 = {
                    navController.navigate(DraggableLettersScreen)
                }
            )
        }
        composable<DraggableLettersScreen>{
            DraggableLettersScreen(onBackClick = {navController.popBackStack()})
        }


    }


}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Screen(
    onNavigateToAnimation1: () -> Unit = {},
){
    ScaffoldWithAppBar(title = "Animations") {
        Box(
            Modifier.padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            FlowRow(
                modifier = Modifier.fillMaxSize()
            ) {
                EllipseButton(text = "DraggableLetters") {
                    onNavigateToAnimation1.invoke()                }

            }
        }
    }
}