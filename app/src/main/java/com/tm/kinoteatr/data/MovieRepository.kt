package com.tm.kinoteatr.data

import com.tm.kinoteatr.data.model.MoviesModel
import com.tm.kinoteatr.data.model.NetworkResult
import com.tm.kinoteatr.data.service.MovieService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.SocketTimeoutException

class MovieRepository {

    suspend fun getTopMovies(): NetworkResult<MoviesModel> {
        return try {
            var response = getService().getTopMovies()

            if (response.code() == 200) {
                NetworkResult.Success(response.body()!!)
            } else {
                NetworkResult.Error(response.message())
            }
        } catch (e: Exception) {
            NetworkResult.Error(e.message)
        } catch (e: SocketTimeoutException) {
            NetworkResult.Error(e.message)
        }
    }

    fun getService(): MovieService {
        return Retrofit.Builder()
            .baseUrl("https://kinopoiskapiunofficial.tech")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieService::class.java)
    }
}