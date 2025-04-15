package com.example.sagemanga

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.sagemanga.data.model.MangaItem
import com.example.sagemanga.ui.screens.MangaListScreen
import com.example.sagemanga.ui.theme.SageMangaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SageMangaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MangaListScreen(
                        onMangaClick = { manga -> handleMangaClick(manga) }
                    )
                }
            }
        }
    }
    
    /**
     * Handles click events on manga items
     * 
     * @param manga The manga item that was clicked
     */
    private fun handleMangaClick(manga: MangaItem) {
        // For now, just show a toast with the manga title
        // In a future version, this could navigate to a detail screen
        Toast.makeText(this, "Selected: ${manga.title}", Toast.LENGTH_SHORT).show()
    }
}
