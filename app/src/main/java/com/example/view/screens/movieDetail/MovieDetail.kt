package com.example.view.screens.movieDetail

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.remember
import androidx.compose.runtime.Composable
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.example.view.R
import com.example.view.ui.theme.Gotham
import androidx.compose.foundation.lazy.items


@Composable
fun MovieDetails(movie: Movie?,navController: NavController){

    var selectedTab by remember { mutableIntStateOf(0)  }
    val seasons = listOf("1.Sezon", "2.Sezon", "3.Sezon", "4.Sezon","5.Sezon")

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
            fontWeight = FontWeight.Bold,
            style = TextStyle(
                fontFamily = Gotham,
                fontWeight = FontWeight.Bold,
                fontSize = 38.sp
            ),
            modifier = Modifier.padding(bottom = 8.dp)

        )
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

        Spacer(modifier = Modifier.height(24.dp))


        Row(verticalAlignment = Alignment.CenterVertically)
        {
                Icon(
                    painter = painterResource(id = R.drawable.play),
                    contentDescription = "Play",
                    tint = Color.White,
                    modifier = Modifier.size(42.dp).clickable{}
                )

            Spacer(modifier = Modifier.width(180.dp))
                Icon(
                    painter = painterResource(id = R.drawable.add),
                    contentDescription = "Add",
                    tint = Color.White,
                    modifier = Modifier.size(36.dp).clickable{}
                )
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

        Row (Modifier.fillMaxWidth()){
            Text(
                text = "Bölümler",
                modifier = Modifier
                    .clickable { selectedTab = 0 },
                color = if (selectedTab == 0) Color.White else Color.Gray,
                style = TextStyle(
                    fontFamily = Gotham,
                    fontWeight = if (selectedTab == 0) FontWeight.Bold else FontWeight.Medium,   // GothamBook ile eşleşiyor
                    fontSize = 20.sp,
                    textDecoration = if (selectedTab == 0) TextDecoration.Underline else TextDecoration.None
                )
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Detaylar",
                modifier = Modifier
                    .clickable { selectedTab == 1 },
                color = if (selectedTab == 1 ) Color.White else Color.Gray,
                style = TextStyle(
                    fontFamily = Gotham,
                    fontWeight = if (selectedTab == 1) FontWeight.Bold else FontWeight.Medium,   // GothamBook ile eşleşiyor
                    fontSize = 20.sp,
                    textDecoration = if (selectedTab == 1) TextDecoration.Underline else TextDecoration.None
                )
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        LazyRow (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {items(seasons) { season ->
            OutlinedButton(
                onClick = {} ,
                modifier = Modifier.size(150.dp, 50.dp),
                shape = RoundedCornerShape(5.dp),
                border = BorderStroke(1.dp, Color.White)
            ) {
                Text(
                    text = season,
                    color = Color.White,
                    style = TextStyle(fontFamily = Gotham)
                )
            }
        }
        }
            }

        }

}