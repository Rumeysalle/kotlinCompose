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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.view.R
import com.example.view.screens.movieDetail.ChannelCards
import com.example.view.screens.movieDetail.Movie
import com.example.view.screens.movieDetail.MovieCard
import com.example.view.screens.movieDetail.MovieDetails

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "RememberReturnType")
@Composable
fun HomeScreen(navController: NavController) {

    val categories = listOf("All", "Series", "Movies", "Kids", "Documentaries", "Catch Up")

    // Movie modelinden liste
    val movies = listOf(
        Movie(
            id = "1",
            title = "Memento",
            description = "A man with short-term memory loss attempts to track down his wife's murderer.",
            placeholderResId = R.drawable.memento
        ),
        Movie(
            id = "2",
            title = "Esaretin Bedeli",
            description = "The story of a banker imprisoned for life for murder.",
            placeholderResId = R.drawable.esaretin_bedeli
        ),
        Movie(
            id = "3",
            title = "Inception",
            description = "A thief who steals corporate secrets through dreams.",
            placeholderResId = R.drawable.inception
        )
    )


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
                        items(movies) { movie ->
                            MovieCard( movie = movie , navController = navController)
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
