package com.example.view.screens.home



import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.view.R

import com.example.view.screens.movieDetail.MovieCard


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel,
    ) {
    // ViewModel'dan film listelerini al
    val upcomingMoviesState by homeViewModel.upcomingMovies.collectAsState()
    val topRatedMoviesState by homeViewModel.topRatedMovies.collectAsState()
    val nowPlayingMoviesState by homeViewModel.nowPlayingMovies.collectAsState()
    val popularMoviesState by homeViewModel.popularMovies.collectAsState()



    Scaffold(
        bottomBar = { /* TODO: BottomBar */ },
        containerColor = Color(0xFF0F0E0E),
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Column {
                TopAppBar(
                    modifier = Modifier.fillMaxWidth(),
                    title = {
                        Text(
                            text = "izlee",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                color = Color.White,
                                fontSize = 24.sp
                            )
                        )
                    },
                    actions = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                painter = painterResource(id = R.drawable.search),
                                contentDescription = "search",
                                tint = Color.White
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFF0F0E0E)
                    )
                )

            }
        }
    ) { innerPadding: PaddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.TopCenter
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {

                // Upcoming Movies Section
                item {
                    MovieCategoryWithCards(
                        title = "Upcoming Movies",
                        movieListUiState = upcomingMoviesState,
                        navController = navController,
                    )
                }

                // Top Rated Movies Section
                item {
                    MovieCategoryWithCards(
                        title = "Top Rated Movies",
                        movieListUiState = topRatedMoviesState,
                        navController = navController,
                    )
                }

                // Now Playing Movies Section
                item {
                    MovieCategoryWithCards(
                        title = "Now Playing Movies",
                        movieListUiState = nowPlayingMoviesState,
                        navController = navController,
                    )
                }

                // Popular Movies Section
                item {
                    MovieCategoryWithCards(
                        title = "Popular Movies",
                        movieListUiState = popularMoviesState,
                        navController = navController,
                    )
                }
            }
        }
    }
}


@Composable
fun MovieCategoryWithCards(
    title: String,
    movieListUiState: MovieListUiState,
    navController: NavController,
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp)) {
        Text(
            modifier = Modifier.padding(start = 16.dp, bottom = 8.dp),
            text = title,
            style = MaterialTheme.typography.bodyLarge.copy(
                color = Color.White, fontSize = 24.sp
            )
        )

        when (val state = movieListUiState) {
            is MovieListUiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Color.White)
                }
            }
            is MovieListUiState.Success -> {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) {
                    items(state.movies) { movie ->
                        MovieCard(movie = movie, navController = navController)
                    }
                }
            }
            is MovieListUiState.Error -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Filmler yüklenirken bir hata oluştu.",
                        color = Color.Red,
                        fontSize = 16.sp
                    )
                }
            }

            else -> {}
        }
    }
}