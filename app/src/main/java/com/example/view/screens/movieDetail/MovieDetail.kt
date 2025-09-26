package com.example.view.screens.movieDetail

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.remember
import androidx.compose.runtime.Composable
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.example.view.R

@Composable
fun MovieDetails(movie: Movie?,navController: NavController){

var selectedTab by remember { mutableIntStateOf(0)  }
Box(
    modifier = Modifier.fillMaxSize(),
    ){
    if(movie?.imageUrl!=null){
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
                            startY = size.height/3,
                            endY = size.height
                        )
                    )
                },
            contentScale = ContentScale.Crop
            )
    } else if (movie?.placeholderResId!=null){
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
                            colors = listOf(Color.Transparent,Color(0xFF0F0E0E)),
                            startY = size.height/3,
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
    Column (
        modifier = Modifier
            .align(Alignment.BottomStart)
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color.Transparent,Color(0xFF0F0E0E)),
                    startY = 0f,
                    endY = 500f
                )
            )
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 32.dp)
    ){
        Text(
            text = movie?.title ?:"",
            color = Color.White,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 8.dp)

        )
        Text(
            text = movie?.description ?:"",
            color = Color.White,
            fontSize = 16.sp,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(16.dp))


        Row(verticalAlignment = Alignment.CenterVertically)
        {
                Icon(
                    painter = painterResource(id = R.drawable.play),
                    contentDescription = "Play",
                    tint = Color.White,
                    modifier = Modifier.clickable{
                    }
                )

            Spacer(modifier = Modifier.width(180.dp))
                Icon(
                    painter = painterResource(id = R.drawable.add),
                    contentDescription = "Add",
                    tint = Color.White,
                    modifier = Modifier.clickable{}
                )

                Icon(
                    painter = painterResource(id = R.drawable.like),
                    contentDescription = "Like",
                    tint = Color.White,
                    modifier = Modifier.clickable{}
                )

                Icon(
                    painter = painterResource(id = R.drawable.send),
                    contentDescription = "Share",
                    tint = Color.White,
                    modifier = Modifier.clickable{}
                )
            Spacer(modifier = Modifier.size(16.dp))

            Text(
                modifier = Modifier.clickable{},
                text ="Bölümler",
                fontSize = 20.sp,
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                modifier = Modifier.clickable{},
                text ="Detaylar",
                fontSize = 20.sp,
                style = MaterialTheme.typography.headlineMedium
            )


        }
    }
}
}