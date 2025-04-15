package com.example.sagemanga.data.model

/**
 * Data class representing a manga item from the API
 */
data class MangaItem(
    val id: String,
    val image: String,
    val title: String,
    val chapter: String,
    val view: String,
    val description: String
)

/**
 * Data class representing the metadata from the API
 */
data class MetaData(
    val totalStories: Int,
    val totalPages: Int,
    val type: List<TypeItem>,
    val state: List<StateItem>,
    val category: List<CategoryItem>
)

/**
 * Data class representing a type item from the API
 */
data class TypeItem(
    val id: String,
    val type: String
)

/**
 * Data class representing a state item from the API
 */
data class StateItem(
    val id: String,
    val type: String
)

/**
 * Data class representing a category item from the API
 */
data class CategoryItem(
    val id: String,
    val type: String
)

/**
 * Data class representing the complete manga list response from the API
 */
data class MangaListResponse(
    val mangaList: List<MangaItem>,
    val metaData: MetaData
)
