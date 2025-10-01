package com.example.view.screens.movieDetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.view.R

@Composable
fun MovieCard(movie: Movie, navController: NavController) {
    ElevatedCard(
        modifier = Modifier
            .size(150.dp, 200.dp)
            .padding(4.dp)
            .clickable {
                navController.navigate(Screen.MovieDetails.createRoute(movie.id))
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column {
            if (movie.imageUrl != null) {
                AsyncImage(
                    model = movie.imageUrl,
                    contentDescription = movie.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(8.dp))
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    MaterialTheme.colorScheme.secondaryContainer,
                                    MaterialTheme.colorScheme.secondaryContainer
                                )
                            )
                        )
                        .clickable {
                            navController.navigate(Screen.MovieDetails.createRoute(movie.id))
                        }
                )
            } else if (movie.placeholderResId != null) {
                Image(
                    painter = painterResource(id = movie.placeholderResId),
                    contentDescription = movie.title,
                    modifier = Modifier
                        .height(150.dp)
                        .fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = movie.title,
                modifier = Modifier.padding(start = 16.dp, end = 8.dp),
                fontSize = 15.sp,
                maxLines = 1,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
@Composable
fun ChannelCards(
){
    val trendingMovies = listOf(
        R.drawable.memento,
        R.drawable.esaretin_bedeli,
        R.drawable.inception
    )

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        items(trendingMovies) { movieRes ->
            ElevatedCard(
                modifier = Modifier
                    .size(240.dp, 140.dp)
                    .padding(4.dp),
                onClick = { /*TODO*/ },
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
            ) {
                // Burada sadece placeholder card var.
            }
}
    }
}
