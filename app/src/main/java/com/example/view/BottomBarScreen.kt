package com.example.view

import androidx.annotation.DrawableRes

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    @DrawableRes val icon: Int,
){
    object Home: BottomBarScreen("home","Home",R.drawable.home)
    object Mylist: BottomBarScreen("mylist","Mylist",R.drawable.my_list)
    object Downloads: BottomBarScreen("downloads","Downloads",R.drawable.download)
    object Profile: BottomBarScreen("profile","Profile",R.drawable.profile)

    companion object{
        val items = listOf(Home,Mylist,Downloads,Profile)
    }

}