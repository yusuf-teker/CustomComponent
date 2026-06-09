package com.yusuf.components.ui.navigation

import androidx.compose.runtime.Composable
import com.yusuf.components.ui.screens.AnimationsScreen
import com.yusuf.components.ui.screens.ClockScreen
import com.yusuf.components.ui.screens.CustomButtonsScreen
import com.yusuf.components.ui.screens.CustomIconsScreen
import com.yusuf.components.ui.screens.DraggableListScreen
import com.yusuf.components.ui.screens.FabButtonsScreen
import com.yusuf.components.ui.screens.ResponsiveTextScreen
import com.yusuf.components.ui.screens.RgbBackgroundScreen
import com.yusuf.components.ui.screens.ScrollingTextScreen
import com.yusuf.components.ui.screens.SearchBarScreen
import com.yusuf.components.ui.screens.SocialMediaItemsScreen
import com.yusuf.components.ui.screens.TabbedScreenScreen

object ScreenResponsiveTextDest : Destination {
    override val route = "responsiveText"
    override val label = "Responsive Text"
    @Composable
    override fun Content(onBack: () -> Unit) {
        ResponsiveTextScreen(onBack)
    }
}

object ScreenScrollingTextDest : Destination {
    override val route = "scrollingText"
    override val label = "Scrolling Text"
    @Composable
    override fun Content(onBack: () -> Unit) {
        ScrollingTextScreen("Sample long text ".repeat(20), onBack)
    }
}

object ScreenTabbedScreenDest : Destination {
    override val route = "tabbedScreen"
    override val label = "Tabbed Screen"
    @Composable
    override fun Content(onBack: () -> Unit) {
        TabbedScreenScreen(onBack)
    }
}

object ScreenSearchBarDest : Destination {
    override val route = "searchBar"
    override val label = "Search Bar"
    @Composable
    override fun Content(onBack: () -> Unit) {
        SearchBarScreen(onBack)
    }
}

object ScreenCustomButtonsDest : Destination {
    override val route = "customButtons"
    override val label = "Custom Buttons"
    @Composable
    override fun Content(onBack: () -> Unit) {
        CustomButtonsScreen(onBack)
    }
}

object ScreenAnimationsDest : Destination {
    override val route = "animations"
    override val label = "Animations"
    @Composable
    override fun Content(onBack: () -> Unit) {
        AnimationsScreen(onBack)
    }
}

object RgbBackgroundScreenDest : Destination {
    override val route = "rgbBackground"
    override val label = "Rgb Background"
    @Composable
    override fun Content(onBack: () -> Unit) {
        RgbBackgroundScreen(onBack)
    }
}

object DraggableListScreenDest : Destination {
    override val route = "draggableList"
    override val label = "Draggable List"
    @Composable
    override fun Content(onBack: () -> Unit) {
        DraggableListScreen(onBack)
    }
}

object ScreenCustomIconsDest : Destination {
    override val route = "customIcons"
    override val label = "Custom Icons"
    @Composable
    override fun Content(onBack: () -> Unit) {
        CustomIconsScreen(onBack)
    }
}


object FabMenuScreenDest : Destination {
    override val route = "fabMenu"
    override val label = "Fab Menu"
    @Composable
    override fun Content(onBack: () -> Unit) {
        FabButtonsScreen(onBack)
    }
}

object ClockScreenDest : Destination {
    override val route = "clockScreen"
    override val label = "Clock Screen"
    @Composable
    override fun Content(onBack: () -> Unit) {
        ClockScreen(onBack)
    }
}

object SocialMediaItemsScreenDest : Destination {
    override val route = "socialMediaItems"
    override val label = "Social Media Items"
    @Composable
    override fun Content(onBack: () -> Unit) {
        SocialMediaItemsScreen(onBack)
    }
}

val allDestinations = listOf(
    ScreenResponsiveTextDest,
    ScreenScrollingTextDest,
    ScreenTabbedScreenDest,
    ScreenSearchBarDest,
    ScreenCustomButtonsDest,
    RgbBackgroundScreenDest,
    DraggableListScreenDest,
    ScreenCustomIconsDest,
    FabMenuScreenDest,ClockScreenDest,
    ScreenAnimationsDest,
    SocialMediaItemsScreenDest
    )
