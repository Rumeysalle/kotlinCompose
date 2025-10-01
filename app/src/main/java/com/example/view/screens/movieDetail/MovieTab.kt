package com.example.view.screens.movieDetail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.view.ui.theme.Gotham
import androidx.compose.runtime.*

@Composable
fun MovieDetailTabs(){
 var selectedPrimaryTab by remember {  mutableIntStateOf(0)  }
 var selectedSeasonIndex by remember { mutableIntStateOf(0) }
 val seasons = listOf("1.Sezon", "2.Sezon", "3.Sezon", "4.Sezon","5.Sezon")
 Column (modifier = Modifier.fillMaxWidth()){

  Row (modifier = Modifier.fillMaxWidth()) {
   TabHeaderItem(
    title = "Bölümler",
    isSelected = selectedPrimaryTab == 0,
    onTabSelected = { selectedPrimaryTab = 0 }
   )

   Spacer(modifier = Modifier.width(16.dp))

   TabHeaderItem(
    title = "Detaylar",
    isSelected = selectedPrimaryTab == 1,
    onTabSelected = { selectedPrimaryTab = 1 }
   )
  }
  Spacer(modifier = Modifier.height(8.dp))

   when(selectedPrimaryTab){
    0 -> {
     Column (modifier = Modifier.fillMaxWidth()){

      LazyRow(
       modifier = Modifier.fillMaxWidth().align(Alignment.Start),
       horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {

        items(seasons.size){ index ->
          SeasonOutlinedButton(
                season = seasons[index],
                isSelected = selectedSeasonIndex == index,
                onSeasonSelected = { selectedSeasonIndex = index }
        )
       }
      }
      Spacer(modifier = Modifier.height(16.dp))
      SeasonContent(season=seasons[selectedSeasonIndex])
     }
    }
    1 -> {
     // DetailContent sayfası gelecek
    }
   }
  }
}
@Composable
fun TabHeaderItem(
 title:String,
 isSelected:Boolean,
 onTabSelected:()->Unit
)
{
 Column (
  modifier = Modifier
  .clickable(onClick = onTabSelected)
  .padding(vertical = 8.dp)){
  Text(
    text = title,
    color = if (isSelected) Color.White else Color.Gray,
    style = TextStyle(
    fontFamily = Gotham,
    color = if (isSelected) Color.White else Color.Gray,
    fontSize = 18.sp,
    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,)
  )
  Spacer(
   modifier = Modifier.width(80.dp)
   .size(3.dp,3.dp)
   .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(5.dp)))
 }
}


@Composable
fun SeasonOutlinedButton(
 season: String,
 isSelected: Boolean,
 onSeasonSelected: () -> Unit
) {
 OutlinedButton(
   onClick = onSeasonSelected,
   modifier = Modifier
   .height(40.dp),
   shape = RoundedCornerShape(5.dp), // Yuvarlak kenarlar
   border = if (isSelected)
   BorderStroke(2.dp, MaterialTheme.colorScheme.primary)
            else
   ButtonDefaults.outlinedButtonBorder,
   colors = ButtonDefaults.outlinedButtonColors(
   contentColor = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface,
   containerColor = if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.1f) else Color.Transparent
  ),
  contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)) {
  Text(
      text= season ,
      color =  if (isSelected) MaterialTheme.colorScheme.primary else Color.White)
 }
}

@Composable
fun SeasonContent(season: String) {
 Column(
  modifier = Modifier
   .fillMaxWidth()
   .padding(top = 8.dp)
 ) {
  LazyColumn(
   modifier = Modifier.fillMaxWidth().height(200.dp), // Sabit bir yükseklik verdik
   verticalArrangement = Arrangement.spacedBy(8.dp)
  ) {
   items(season.length) { index ->
    EpisodeListItem( episodeTitle = "$season - Bölüm ${index + 1}")
   }
  }
 }
}

@Composable
fun EpisodeListItem(episodeTitle: String) {
 Row(
  modifier = Modifier
   .fillMaxWidth()
   .background(MaterialTheme.colorScheme.surfaceVariant,
    RoundedCornerShape(8.dp))
   .padding(12.dp),
  verticalAlignment = Alignment.CenterVertically
 ) {
  Text(
   text = "",
   fontWeight = FontWeight.Bold,
   fontSize = 16.sp,
   color = MaterialTheme.colorScheme.onSurfaceVariant
  )
  Spacer(modifier = Modifier.width(8.dp))
  Text(
   text = episodeTitle,
   fontSize = 16.sp,
   color = MaterialTheme.colorScheme.onSurfaceVariant
  )
  // Buraya bölümün oynat butonu, süresi vb. eklenebilir.
 }
}

