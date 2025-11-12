package com.example.view.screens.movieDetail


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column // Column eklendi
import androidx.compose.foundation.layout.FlowRow // FlowRow eklendi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.compose.collectAsStateWithLifecycle


import com.example.view.R
import com.example.view.ui.theme.Gotham // Gotham stilinizin tanımlı olduğundan emin olun


@Composable
fun MovieDetails(
    navController: NavController,
    detailViewModel: DetailViewModel
) {
    val uiState by detailViewModel.movieUiState.collectAsStateWithLifecycle()
    val isFavorite by detailViewModel.isFavorite.collectAsState()

    LaunchedEffect(Unit) {
        detailViewModel.loadMovieById()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        when (uiState) {
            is MovieUiState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Loading movie details...", color = Color.White)
                }
            }
            is MovieUiState.Error -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Error loading movie details.", color = Color.Red)
                }
            }
            is MovieUiState.Success -> {
                val movie = (uiState as MovieUiState.Success).movieDetail

                movie?.poster_path?.let { posterPath ->
                    AsyncImage(
                        model = posterPath,
                        contentDescription = movie.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(500.dp)
                            .drawWithContent {
                                drawContent()
                                drawRect(
                                    brush = Brush.verticalGradient(
                                        colors = listOf(Color.Transparent, Color(0xFF0F0E0E)),
                                        startY = size.height / 3,
                                        endY = size.height
                                    )
                                )
                            },
                        contentScale = ContentScale.Crop
                    )
                }

                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier
                        .padding(16.dp)
                        .size(40.dp)
                        .align(Alignment.TopStart)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }

                LazyColumn(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color(0xFF0F0E0E)),
                                startY = 0f,
                                endY = 500f
                            )
                        )
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 32.dp)
                ) {
                    item {
                        Text(
                            text = movie?.title ?: "",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            style = TextStyle(
                                fontFamily = Gotham,
                                fontWeight = FontWeight.Bold,
                                fontSize = 38.sp
                            ),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    // Derecelendirme, Yetişkin Bilgisi, Çalışma Süresi ve Türler
                    item {
                        Column(modifier = Modifier.padding(bottom = 16.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                movie?.vote_average?.let { rating ->
                                    Text(
                                        text = String.format("%.1f", rating),
                                        color = Color.Green, // Derecelendirme için farklı bir renk
                                        style = TextStyle(
                                            fontFamily = Gotham,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 18.sp
                                        ),
                                        modifier = Modifier.padding(end = 16.dp)
                                    )
                                }
                               movie?.release_date?.let { release_date ->
                                   Text(
                                       text = release_date,
                                       color = Color.White.copy(alpha = 0.7f),
                                       style = TextStyle(
                                           fontFamily = Gotham,
                                           fontWeight = FontWeight.Medium,
                                           fontSize = 16.sp
                                       )
                                   )
                               }
                                Spacer(modifier = Modifier.width(16.dp))
                                movie?.runtime?.let { runtime ->
                                    Text(
                                        text = formatRuntime(runtime),
                                        color = Color.White.copy(alpha = 0.7f),
                                        style = TextStyle(
                                            fontFamily = Gotham,
                                            fontWeight = FontWeight.Medium,
                                            fontSize = 16.sp
                                        )
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            movie?.genres?.let { genres ->
                                LazyRow(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(16.dp)
                                ) {
                                    item {
                                        genres.forEach { genre ->
                                            Text(
                                                text = genre.name,
                                                color = Color.White.copy(alpha = 0.7f),
                                                style = TextStyle(
                                                    fontFamily = Gotham,
                                                    fontWeight = FontWeight.Medium,
                                                    fontSize = 14.sp
                                                ),
                                                modifier = Modifier.padding(end = 16.dp)
                                                    .background(Color.Gray.copy(alpha = 0.3f), shape = androidx.compose.foundation.shape.RoundedCornerShape(4.dp))
                                                    .padding(horizontal = 8.dp, vertical = 2.dp)
                                            )
                                        }
                                    }

                                }
                            }
                        }
                    }

                    item { Spacer(modifier = Modifier.height(24.dp)) }

                    item {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(id = R.drawable.play),
                                contentDescription = "Play",
                                tint = Color.White,
                                modifier = Modifier
                                    .size(42.dp)
                                    .clickable { /* Handle play click */ }
                            )

                            Spacer(modifier = Modifier.weight(1f)) // Boşluk için weight kullanıldı

                            IconButton(
                                onClick = {
                                    detailViewModel.toggleFavorite()
                                }
                            ) {
                                Icon(
                                    imageVector = if (isFavorite) Icons.Default.Done else Icons.Default.Add,
                                    contentDescription = if (isFavorite) "Remove from favorites" else "Add to favorites",
                                    tint = if (isFavorite) Color.Green else Color.White,
                                    modifier = Modifier.size(36.dp)
                                )
                            }


                            Spacer(modifier = Modifier.width(16.dp))
                            Icon(
                                painter = painterResource(id = R.drawable.like),
                                contentDescription = "Like",
                                tint = Color.White,
                                modifier = Modifier
                                    .size(32.dp)
                                    .clickable { /* Handle like click */ }
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Icon(
                                painter = painterResource(id = R.drawable.send),
                                contentDescription = "Share",
                                tint = Color.White,
                                modifier = Modifier
                                    .size(36.dp)
                                    .clickable { /* Handle share click */ }
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                        }
                        Spacer(modifier = Modifier.height(24.dp))
                    }

                    // Overview bilgisi iconların altında
                    item {
                        Text(
                            text = movie?.overview ?: "",
                            color = Color.White,
                            style = TextStyle(
                                fontFamily = Gotham,
                                fontWeight = FontWeight.Normal, // Normal veya Medium kullanabilirsiniz
                                fontSize = 16.sp
                            ),
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                    }
                }
            }
        }
    }

}
fun formatRuntime(runtime: Int): String {
    val hours = runtime / 60
    val minutes = runtime % 60

    return if (hours > 0) {
        "${hours}h ${minutes}m"
    } else {
        "${minutes}m"
    }
}
