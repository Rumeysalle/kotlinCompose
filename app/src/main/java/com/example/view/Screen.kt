package com.example.view

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

sealed class Screen(
    val route: String,
    val title: String,
    @DrawableRes val icon: Int? = null,
) {
    data object Home : Screen("home", "Home", R.drawable.home)
    data object Mylist : Screen("mylist", "Mylist", R.drawable.my_list)
    data object Downloads : Screen("downloads", "Downloads", R.drawable.download)
    data object Profile : Screen("profile", "Profile", R.drawable.profile)
    data object MovieDetails : Screen("movie_details/{movieId}", "MovieDetail")
    data object MoviePlayer : Screen("movie_player", "MoviePlayer")

    companion object {
        val bottomBarItems = listOf(Home, Mylist, Downloads, Profile)
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    NavigationBar(
        containerColor = Color(0xFF1A1A1A),
    ) {
        val navBackStackEntry = navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry.value?.destination

        Screen.bottomBarItems.forEach { screen ->
            val isSelected = currentDestination?.hierarchy?.any { it.route == screen.route } == true

            NavigationBarItem(
                icon = {
                    if (screen.icon != null)
                        Icon(
                            painter = painterResource(id = screen.icon),
                            contentDescription = screen.title,
                            modifier = Modifier.size(24.dp),
                            tint = if (isSelected) Color.White else Color.Gray
                        )
                },
                label = {
                    Text(
                        text = screen.title,
                        fontSize = 12.sp,
                        color = if (isSelected) Color.White else Color.Gray
                    )
                },
                selected = isSelected,
                onClick = {
                    navController.navigate(screen.route) {

                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = false
                    }

                },
            )
        }
    }
}