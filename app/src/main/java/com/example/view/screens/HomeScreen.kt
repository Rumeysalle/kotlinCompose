package com.example.view.screens

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
import com.example.view.R





@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "RememberReturnType")
@Composable
fun HomeScreen() {
    val itemsList = (0..5).toList()
    val items = listOf("All", "Series", "Movies", "Kids", "Documentaries", "Catch Up")

    Scaffold(
        bottomBar = {

        },
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

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
                {
                    items(itemsList) { item: Int ->
                        Text(
                            text = items[item],
                            style = MaterialTheme.typography.bodyLarge.copy
                                (color = Color.White, fontSize = 18.sp),
                            modifier = Modifier.background(color = Color(0xFF0F0E0E))
                        )
                    }
                }

            }
        }
    )
    { innerPadding: PaddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),

                ) {
                item {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                    {
                        items(5) { index: Int ->
                            ElevatedCard(
                                modifier = Modifier
                                    .size(140.dp, 200.dp)
                                    .padding(4.dp),
                                onClick = { /*TODO*/ },
                                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)

                            ) { /*TODO*/ }
                        }
                    }
                }
                item { Column(
                    modifier = Modifier.fillMaxSize(),
                )
                {
                    Text(
                        modifier = Modifier.padding(start = 8.dp, top = 8.dp),
                        text = "Live TV > ",
                        style = MaterialTheme.typography.bodyLarge.copy
                            (color = Color.White, fontSize = 24.sp)
                    )
                    Spacer(modifier = Modifier.padding(4.dp))

                    Text(
                        modifier = Modifier.padding(start = 8.dp, top = 8.dp),
                        text = "All Channels",
                        style = MaterialTheme.typography.bodyLarge.copy
                            (color = Color.White, fontSize = 20.sp)
                    )
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                    {
                        items(5) { index: Int ->
                            ElevatedCard(
                                modifier = Modifier
                                    .size(240.dp, 140.dp)
                                    .padding(4.dp),
                                onClick = { /*TODO*/ },
                                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)

                            ) { /*TODO*/ }
                        }
                    }

                } }

            }

        }
    }
}

