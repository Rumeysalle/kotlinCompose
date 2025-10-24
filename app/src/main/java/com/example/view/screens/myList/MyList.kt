package com.example.view.screens.myList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.compose.runtime.getValue
import com.example.view.screens.home.HomeViewModel
import com.example.view.screens.movieDetail.Movie
import com.example.view.screens.movieDetail.MovieCard
import com.example.view.ui.theme.Gotham

@Composable
fun MyList(navController: NavController,myListViewModel: MyListViewModel){

    val favoriteMovies by myListViewModel.favorites.collectAsState()

    LaunchedEffect(Unit) {
        // dinamik API veya güncelleme alırsam burada tekrar çağıracağım.
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
    item {
        Box (modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)){

                Text(
                    text = "Edit",
                    color = Color.White,
                    modifier = Modifier.align(Alignment.TopEnd).clickable{
                        //Edit işlemleri burada yapılır
                    },
                    style = TextStyle(
                        fontFamily = Gotham,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp),
                    )
                 Text(
                     text = "My List",
                     color = Color.White,
                     modifier = Modifier.align(Alignment.BottomStart).padding(top = 40.dp),
                     style = TextStyle(
                         fontFamily = Gotham,
                         fontWeight = FontWeight.Bold,
                         fontSize = 28.sp),
            )
        }
       }
        items(favoriteMovies) { movie ->
            MovieCard(
                movie = movie,
                navController = navController,
            )
        }


    }



}

