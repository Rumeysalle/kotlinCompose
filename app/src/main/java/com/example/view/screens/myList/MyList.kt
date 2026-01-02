package com.example.view.screens.myList


import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.view.screens.movieDetail.MovieCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyListScreen(
    navController: NavController,
    viewModel: MyListViewModel,
    onBack: () -> Unit,
    onMovieClick: (Int) -> Unit
) {
    val favorites by viewModel.favorites.collectAsStateWithLifecycle()
    val isEditMode by viewModel.isEditMode.collectAsStateWithLifecycle()

    Scaffold(
        containerColor = Color(0xFF0F0E0E),
        topBar = {
            TopAppBar(
                title = { Text(
                    text = "izlee",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = Color.White,
                        fontSize = 24.sp
                    )
                )
                        },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF0F0E0E)
                ),
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )

                    }
                },
                actions = {
                    TextButton(onClick = { viewModel.toggleEditMode() }) {
                        Text(if (isEditMode) "Done" else "Edit")
                    }
                }
            )
        }

    ) { padding ->
        if (favorites.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ) {
                Text("No favorites yet")
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentPadding = PaddingValues(12.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(
                    items = favorites,
                    key = { it.id }
                ) { movie ->
                    MovieCard(
                        movie = movie,
                        isFavorite = true,
                        isEditMode = isEditMode,
                        onClick = {
                            // Sadece düzenleme modu kapalıysa detay sayfasına git
                            if (!isEditMode) {
                                onMovieClick(movie.id)
                            }
                        },
                        onRemoveClick = {
                            viewModel.deleteFavorite(movie.id)
                        }
                    )
                }
            }
        }
    }
}
