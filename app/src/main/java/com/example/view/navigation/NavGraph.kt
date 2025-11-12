package com.example.view.navigation

import HomeViewModel
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.view.screens.Downloads
import com.example.view.screens.ProfileScreen
import com.example.view.screens.home.HomeScreen
import com.example.view.screens.movieDetail.DetailViewModel
import com.example.view.screens.movieDetail.MovieDetails


@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.Home.route,
    navActions: ViewNavigationAction = remember(navController) {
        ViewNavigationAction(navController)
    }
) {

    val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentNavBackStackEntry?.destination?.route ?: startDestination


    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(Screen.Home.route) {
            val homeViewModel: HomeViewModel = viewModel()
            HomeScreen(navController = navController,homeViewModel )
        }


        composable(Screen.Downloads.route) { Downloads() }

        composable(Screen.Profile.route) { ProfileScreen() }
        composable(
            route = "movieDetail/{movieId}",
            arguments = listOf(
                navArgument("movieId") { type = NavType.IntType },
            )
        ) { backStackEntry ->
            val viewModel: DetailViewModel = viewModel(
                viewModelStoreOwner = backStackEntry
            )
            MovieDetails(navController, viewModel)
        }

    }
}
