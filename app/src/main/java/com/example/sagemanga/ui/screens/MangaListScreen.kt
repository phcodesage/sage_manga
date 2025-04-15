package com.example.sagemanga.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sagemanga.data.model.MangaItem
import com.example.sagemanga.ui.components.MangaCard
import com.example.sagemanga.ui.viewmodel.MangaUiState
import com.example.sagemanga.ui.viewmodel.MangaViewModel

/**
 * Main screen that displays the list of manga
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MangaListScreen(
    viewModel: MangaViewModel = viewModel(),
    onMangaClick: (MangaItem) -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Sage Manga") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (val state = uiState) {
                is MangaUiState.Loading -> {
                    // Show loading indicator
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                
                is MangaUiState.Success -> {
                    // Show manga list
                    if (state.mangaList.isEmpty()) {
                        // Show empty state
                        Text(
                            text = "No manga found",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .align(Alignment.Center),
                            textAlign = TextAlign.Center
                        )
                    } else {
                        // Show manga list
                        LazyColumn(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            items(state.mangaList) { manga ->
                                MangaCard(
                                    manga = manga,
                                    onClick = onMangaClick
                                )
                            }
                            // Add some space at the bottom
                            item { Spacer(modifier = Modifier.height(16.dp)) }
                        }
                    }
                }
                
                is MangaUiState.Error -> {
                    // Show error state with retry button
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                            .align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Error: ${state.message}",
                            color = MaterialTheme.colorScheme.error,
                            textAlign = TextAlign.Center
                        )
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        Button(onClick = { viewModel.retryLoading() }) {
                            Text("Retry")
                        }
                    }
                }
            }
        }
    }
}
