package com.example.sagemanga.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Object responsible for providing network-related dependencies
 */
object NetworkModule {
    
    // Base URL for the Manga Hook API
    // Using a public instance of the API
    private const val BASE_URL = "https://mangahook-api.vercel.app/"
    
    /**
     * Creates and configures an OkHttpClient instance
     */
    private fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }
    
    /**
     * Creates and configures a Retrofit instance
     */
    private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    
    /**
     * Provides an instance of the MangaApiService
     */
    val mangaApiService: MangaApiService by lazy {
        provideRetrofit(provideOkHttpClient()).create(MangaApiService::class.java)
    }
}
