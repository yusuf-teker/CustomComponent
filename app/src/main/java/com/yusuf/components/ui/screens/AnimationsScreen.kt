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
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yusuf.components.ui.navigation.Destination
import com.yusuf.components.ui.screens.animations.DraggableLettersScreen
import com.yusuf.components.ui.util.ScaffoldWithAppBar
import kotlinx.serialization.Serializable


@Serializable
object ScreenAnimations // Base Ekran

@Composable
fun AnimationsScreen(onBackClick: () -> Unit = {}) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ScreenAnimations
    ){

        composable<ScreenAnimations>{ // Base Ekran
            Screen(
               nav = navController,
                onBackClick = onBackClick
            )
        }
        allAnimationsDestination.forEach { dest -> // Child Ekranlar
            composable(dest.route) {
                dest.Content { navController.popBackStack() }
            }
        }


    }


}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Screen(
    onBackClick: () -> Unit = {},
    nav: NavController = rememberNavController(),
){
    ScaffoldWithAppBar(title = "Animations", onBackClick = {onBackClick.invoke()}) {


        Box(
            Modifier.padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            FlowRow(
                modifier = Modifier.fillMaxSize()
            ) {

                allAnimationsDestination.forEach { dest ->
                    EllipseButton(dest.label) { nav.navigate(dest.route) }
                }              }
            }
        }

}

object DraggableLettersDest : Destination {
    override val route = "draggableLetters"
    override val label = "Draggable Letters"
    @Composable
    override fun Content(onBack: () -> Unit) {
       DraggableLettersScreen(onBack)
    }
}

val allAnimationsDestination = listOf(
    DraggableLettersDest
)
