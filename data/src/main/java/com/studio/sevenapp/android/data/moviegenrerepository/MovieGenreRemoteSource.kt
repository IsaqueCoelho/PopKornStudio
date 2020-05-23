package com.studio.sevenapp.android.data.moviegenrerepository

import retrofit2.http.GET

interface MovieGenreRemoteSource {
    @GET("genre/movie/list")
    suspend fun getCategoryMovies() : MovieGenreObjectResponse
}