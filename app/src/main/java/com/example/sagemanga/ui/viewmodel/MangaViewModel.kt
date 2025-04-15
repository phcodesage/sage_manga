package com.example.sagemanga.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sagemanga.data.model.MangaItem
import com.example.sagemanga.data.repository.MangaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for managing manga data and UI state
 */
class MangaViewModel : ViewModel() {
    
    private val repository = MangaRepository()
    
    // UI state
    private val _uiState = MutableStateFlow<MangaUiState>(MangaUiState.Loading)
    val uiState: StateFlow<MangaUiState> = _uiState.asStateFlow()
    
    init {
        loadMangaList()
    }
    
    /**
     * Loads the manga list from the repository
     */
    fun loadMangaList(
        page: Int? = null,
        type: String? = null,
        state: String? = null,
        category: String? = null
    ) {
        _uiState.value = MangaUiState.Loading
        
        viewModelScope.launch {
            repository.getMangaList(page, type, state, category)
                .onSuccess { response ->
                    _uiState.value = MangaUiState.Success(response.mangaList)
                }
                .onFailure { error ->
                    _uiState.value = MangaUiState.Error(error.message ?: "Unknown error occurred")
                }
        }
    }
    
    /**
     * Retry loading the manga list after an error
     */
    fun retryLoading() {
        loadMangaList()
    }
}

/**
 * Sealed interface representing the different states of the UI
 */
sealed interface MangaUiState {
    /**
     * Loading state when data is being fetched
     */
    object Loading : MangaUiState
    
    /**
     * Success state when data has been successfully fetched
     * 
     * @param mangaList The list of manga items
     */
    data class Success(val mangaList: List<MangaItem>) : MangaUiState
    
    /**
     * Error state when data fetching has failed
     * 
     * @param message The error message
     */
    data class Error(val message: String) : MangaUiState
}
