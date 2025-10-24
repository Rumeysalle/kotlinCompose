package com.example.view.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination

class ViewNavigationAction(private val navController: NavController){

    fun navigateToMovie(){
        navController.navigate(Screen.Home.route){
            popUpTo(navController.graph.findStartDestination().id){
                inclusive = true
                saveState = true
            }
            launchSingleTop = true
            restoreState= true
        }

    }

    fun navigateToDetail(){
        navController.navigate(Screen.MovieDetails.route){
            popUpTo(navController.graph.findStartDestination().id){
                inclusive = false
                saveState = true
            }
            launchSingleTop= true
            restoreState = true

    }
}
    fun navigateMylist(){
        navController.navigate(Screen.Mylist.route){
            popUpTo(navController.graph.findStartDestination().id){
                inclusive = true
                saveState = true
            }
            launchSingleTop = true
            restoreState= true

        }
    }
}