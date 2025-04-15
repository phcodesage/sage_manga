package com.example.sagemanga.data.api

import com.example.sagemanga.data.model.MangaListResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit API service interface for manga data
 */
interface MangaApiService {
    
    /**
     * Get a list of manga
     * 
     * @param page Optional page number for pagination
     * @param type Optional type filter (e.g., "newest")
     * @param state Optional state filter (e.g., "Completed")
     * @param category Optional category filter (e.g., "all")
     * @return MangaListResponse containing the list of manga and metadata
     */
    @GET("api/mangaList")
    suspend fun getMangaList(
        @Query("page") page: Int? = null,
        @Query("type") type: String? = null,
        @Query("state") state: String? = null,
        @Query("category") category: String? = null
    ): MangaListResponse
}
