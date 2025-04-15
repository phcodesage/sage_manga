package com.example.sagemanga.data.repository

import com.example.sagemanga.data.api.NetworkModule
import com.example.sagemanga.data.model.MangaItem
import com.example.sagemanga.data.model.MangaListResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Repository class that handles manga data operations
 */
class MangaRepository {
    private val apiService = NetworkModule.mangaApiService
    
    /**
     * Fetches the list of manga from the API
     * 
     * @param page Optional page number for pagination
     * @param type Optional type filter (e.g., "newest")
     * @param state Optional state filter (e.g., "Completed")
     * @param category Optional category filter (e.g., "all")
     * @return Result containing either the MangaListResponse or an exception
     */
    suspend fun getMangaList(
        page: Int? = null,
        type: String? = null,
        state: String? = null,
        category: String? = null
    ): Result<MangaListResponse> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.getMangaList(page, type, state, category)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
