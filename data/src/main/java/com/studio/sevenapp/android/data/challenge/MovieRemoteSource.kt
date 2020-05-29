package com.studio.sevenapp.android.data.challenge

import com.studio.sevenapp.android.domain.model.MovieObjectResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieRemoteSource {
    @GET("discover/movie")
    suspend fun getMovies(
        @Query("sort_by") sortBy: String,
        @Query("include_adult") includeAdult: Boolean,
        @Query("page") page: Int,
        @Query("with_genres") withGenres: Int
    ): MovieObjectResponse
}