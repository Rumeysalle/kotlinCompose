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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.view.R
import com.example.view.domain.model.Movie


@Composable
fun MovieCard(
    movie: Movie,
    navController: NavController,
) {
    ElevatedCard(
        modifier = Modifier
            .size(150.dp, 200.dp)
            .padding(4.dp)
            .clickable {
                navController.navigate(Screen.MovieDetails.createRoute(movie.id.toString()))
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // posterUrl'in her zaman bir URL olacağı varsayıldığı için if bloğu kaldırıldı.
            AsyncImage(
                model = movie.posterUrl,
                contentDescription = movie.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                    .clickable {
                        navController.navigate(Screen.MovieDetails.createRoute(movie.id.toString()))
                    }
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Film ismini gösteriyoruz
            Text(
                text = movie.title,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 4.dp),
                fontSize = 15.sp,
                maxLines = 1,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black
            )
        }
    }
}
