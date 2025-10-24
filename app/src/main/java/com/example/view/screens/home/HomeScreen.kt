package com.example.view.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.view.R
import com.example.view.screens.movieDetail.ChannelCards
import com.example.view.screens.movieDetail.Movie
import com.example.view.screens.movieDetail.MovieCard



@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "RememberReturnType")
@Composable
fun HomeScreen(navController: NavController, homeViewModel: HomeViewModel) {
    val categories = listOf("All", "Series", "Movies", "Kids", "Documentaries", "Catch Up")

    // ViewModeldan bilgileri al
    val movieList by homeViewModel.movieList.collectAsState()

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
                            text = "tabii",
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

                // Kategoriler
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    items(categories) { category ->
                        Text(
                            text = category,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                color = Color.White, fontSize = 18.sp
                            ),
                            modifier = Modifier.background(color = Color(0xFF0F0E0E))
                        )
                    }
                }
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
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {

                // 🎬 Trending Movies (MovieCard ile gösterim)
                item {
                    Text(
                        modifier = Modifier.padding(start = 8.dp, top = 8.dp),
                        text = "Trending Movies",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = Color.White, fontSize = 24.sp
                        )
                    )

                    Spacer(modifier = Modifier.padding(4.dp))

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.padding(vertical = 4.dp)
                    ) {
                        items(movieList) { movie ->
                            MovieCard(movie = movie, navController = navController)
                        }
                    }
                }

                // 📺 Live TV Section
                item {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        Text(
                            modifier = Modifier.padding(start = 8.dp, top = 8.dp),
                            text = "Live TV > ",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                color = Color.White, fontSize = 24.sp
                            )
                        )
                        Spacer(modifier = Modifier.padding(4.dp))

                        Text(
                            modifier = Modifier.padding(start = 8.dp, top = 8.dp),
                            text = "All Channels",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                color = Color.White, fontSize = 20.sp
                            )
                        )
                            }
                    ChannelCards()
                        }
                    }
                }
    }
}
