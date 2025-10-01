package com.example.view.screens.movieDetail

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.view.R
import com.example.view.data.FavoriteLists
import com.example.view.ui.theme.Gotham
import androidx.compose.runtime.*


@Composable
fun MovieDetails(movie: Movie?,navController: NavController){
    var isFavorite by remember { mutableStateOf(false) }
Box(
    modifier = Modifier.fillMaxSize(),
    ) {
    if (movie?.imageUrl != null) {
        AsyncImage(
            model = movie.imageUrl,
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
    } else if (movie?.placeholderResId != null) {
        Image(
            painter = painterResource(id = movie.placeholderResId),
            contentDescription = movie.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
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
            contentDescription = "",
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
    ) {item {
        Text(
            text = movie?.title ?:"",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            style = TextStyle(
                fontFamily = Gotham,
                fontWeight = FontWeight.Bold,
                fontSize = 38.sp
            ),
            modifier = Modifier.padding(bottom = 8.dp)

        )
    }
   item {
       Text(
           text = movie?.description ?:"",
           color = Color.White,
           maxLines = 3,
           overflow = TextOverflow.Ellipsis,
           style = TextStyle(
               fontFamily = Gotham,
               fontWeight = FontWeight.Medium,
               fontSize = 16.sp
           ),
       )
   }


       item { Spacer(modifier = Modifier.height(24.dp))  }


       item {
           Row(verticalAlignment = Alignment.CenterVertically)
           {
               Icon(
                   painter = painterResource(id = R.drawable.play),
                   contentDescription = "Play",
                   tint = Color.White,
                   modifier = Modifier.size(42.dp).clickable{}
               )

               Spacer(modifier = Modifier.width(180.dp))
               IconButton(
                   onClick = {isFavorite=!isFavorite}

               ) {
                   Icon(
                      imageVector = if (isFavorite) Icons.Default.Done else Icons.Default.Add,
                       contentDescription = null,
                       tint = if (isFavorite) Color.Green else Color.White,
                       modifier = Modifier.size(36.dp).clickable{ movie?.let { FavoriteLists.addMovie(it.id) }}
                   )
               }
               Spacer(modifier = Modifier.width(16.dp))
               Icon(
                   painter = painterResource(id = R.drawable.like),
                   contentDescription = "Like",
                   tint = Color.White,
                   modifier = Modifier.size(36.dp).clickable{}
               )
               Spacer(modifier = Modifier.width(16.dp))
               Icon(
                   painter = painterResource(id = R.drawable.send),
                   contentDescription = "Share",
                   tint = Color.White,
                   modifier = Modifier.size(36.dp).clickable{}
               )
               Spacer(modifier = Modifier.size(16.dp))

           }
           Spacer(modifier = Modifier.height(24.dp))
       }

      item {
          MovieDetailTabs()
      }
    }
    }

}