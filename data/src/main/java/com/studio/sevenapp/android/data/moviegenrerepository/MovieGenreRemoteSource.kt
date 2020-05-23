package com.studio.sevenapp.android.data.moviegenrerepository

import retrofit2.http.GET

interface MovieGenreRemoteSource {
    @GET("genre/movie/list?api_key=ba08e18c56c2fc61089c9fdc2a17c645&language=pt-BR")
    suspend fun getCategoryMovies() : MovieGenreObjectResponse
}