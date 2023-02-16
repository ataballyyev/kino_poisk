package com.tm.kinoteatr.data.service

import com.tm.kinoteatr.data.model.MoviesModel
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MovieService {

    @GET("/api/v2.2/films/top")
    suspend fun getTopMovies(
        @Header("accept") accept: String = "application/json",
        @Header("X-API-KEY") apiKey: String = "YOUR_API_KEY",
        @Query("type") type: String = "TOP_250_BEST_FILMS",
        @Query("page") page: Int = 1
    ): Response<MoviesModel>

}