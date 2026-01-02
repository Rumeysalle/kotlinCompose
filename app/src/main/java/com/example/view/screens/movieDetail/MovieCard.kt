package com.example.view.screens.movieDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.view.domain.model.Movie


@Composable
fun MovieCard(
    movie: Movie,
    isFavorite: Boolean = false,
    isEditMode: Boolean = false,
    onClick: () -> Unit,
    onRemoveClick: () -> Unit = {}
) {
    ElevatedCard(
        modifier = Modifier
            .size(150.dp, 200.dp).fillMaxWidth()
            .padding(4.dp)
            .clickable {
                onClick()
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        shape = RoundedCornerShape(8.dp)
    ) {

        Box(modifier = Modifier.fillMaxSize())
        {
        Column(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = movie.posterUrl,
                contentDescription = movie.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))

            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = movie.title,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 4.dp),
                fontSize = 15.sp,
                maxLines = 1,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black
            )

        }

            if (isEditMode) {
                IconButton(
                    onClick = onRemoveClick,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .background(Color.Black.copy(alpha = 0.5f), CircleShape)
                        .size(30.dp)
                ) {
                    Icon(
                        imageVector =Icons.Default.Close,
                        contentDescription = "Remove",
                        tint = Color.White,
                        modifier = Modifier.padding(4.dp)
                    )
                }
            }

        }
    }
}
