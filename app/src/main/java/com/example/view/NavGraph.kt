package com.example.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.view.screens.Downloads
import com.example.view.screens.HomeScreen
import com.example.view.screens.MyList
import com.example.view.screens.ProfileScreen


@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
    startDestination: String = BottomBarScreen.Home.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    )
    {
        composable(BottomBarScreen.Home.route) { HomeScreen() }
        composable(BottomBarScreen.Mylist.route) { MyList() }
        composable(BottomBarScreen.Downloads.route) { Downloads() }
        composable(BottomBarScreen.Profile.route) { ProfileScreen() }
    }
}
