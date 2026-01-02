package com.example.view.navigation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.view.player.MoviePlayerScreen
import com.example.view.Screen
import com.example.view.screens.Downloads
import com.example.view.screens.ProfileScreen
import com.example.view.screens.home.HomeScreen
import com.example.view.screens.home.HomeViewModel
import com.example.view.screens.movieDetail.DetailViewModel
import com.example.view.screens.movieDetail.MovieDetails
import com.example.view.screens.myList.MyListScreen
import com.example.view.screens.myList.MyListViewModel


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
            val homeViewModel: HomeViewModel = hiltViewModel()
            HomeScreen(navController = navController, homeViewModel, onMovieClick = { movieId ->
                navController.navigate("movie_details/$movieId")
            } )
        }
        composable(Screen.MoviePlayer.route) { MoviePlayerScreen(videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4") }
        composable(Screen.Downloads.route) { Downloads() }
        composable(Screen.Mylist.route){
            val viewModel: MyListViewModel = hiltViewModel()
            MyListScreen(
                navController,
                viewModel,
                onBack = { navController.popBackStack()},
                onMovieClick = { movieId ->
                navController.navigate("movie_details/$movieId")
        } )
        }
        composable(Screen.Profile.route) { ProfileScreen() }
        composable(
            Screen.MovieDetails.route,
            arguments = listOf(navArgument("movieId")
            { type = NavType.IntType },)
        )
        { backStackEntry ->
                val viewModel: DetailViewModel = hiltViewModel(viewModelStoreOwner = backStackEntry)
                MovieDetails(
                    navController,
                    viewModel,
                    onMovieClick = {  navController.navigate("movie_player")})
            }

    }
}
