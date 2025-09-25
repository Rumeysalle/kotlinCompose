package com.example.view

import androidx.annotation.DrawableRes

sealed class Screen(
    val route: String,
    val title: String,
    @DrawableRes val icon: Int,
){
    object Home: Screen("home","Home",R.drawable.home)
    object Mylist: Screen("mylist","Mylist",R.drawable.my_list)
    object Downloads: Screen("downloads","Downloads",R.drawable.download)
    object Profile: Screen("profile","Profile",R.drawable.profile)

    object MovieDetail : Screen("movieDetail/{movieId}","MovieDetail",R.drawable.memento) {
        fun createRoute(movieId: String) = "imageUrl/$movieId"
    }

    companion object{
        val bottomBarItems = listOf(Home, Mylist, Downloads, Profile)
    }

}