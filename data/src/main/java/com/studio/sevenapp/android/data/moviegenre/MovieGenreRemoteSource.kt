package com.studio.sevenapp.android.data.moviegenre

import retrofit2.http.GET

interface MovieGenreRemoteSource {
    @GET("genre/movie/list")
    suspend fun getCategoryMovies() : MovieGenreObjectResponse
}