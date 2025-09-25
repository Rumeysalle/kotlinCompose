package com.example.view.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.view.Screen
import com.example.view.screens.Downloads
import com.example.view.screens.home.HomeScreen
import com.example.view.screens.myList.MyList
import com.example.view.screens.ProfileScreen
import com.example.view.screens.movieDetail.MovieDetails


@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
    startDestination: String = Screen.Home.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    )
    {
        composable(Screen.Home.route) { HomeScreen(navController) }
        composable(Screen.Mylist.route) { MyList() }
        composable(Screen.Downloads.route) { Downloads() }
        composable(Screen.Profile.route) { ProfileScreen() }
        composable(
            "movie_details/{placeholderResId}",
            arguments = listOf(navArgument("placeholderResId") { type = NavType.IntType })
        ) { backStackEntry ->
            val resId = backStackEntry.arguments?.getInt("placeholderResId")
            MovieDetails(placeholderRes = resId)
        }
}
}
