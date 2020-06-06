package com.studio.sevenapp.android.data.moviegenre

import com.studio.sevenapp.android.data.moviegenre.localsource.GenreLocalSource
import com.studio.sevenapp.android.domain.model.Genre
import com.studio.sevenapp.android.domain.moviegenre.MovieGenreRepository

class MovieGenreRepositoryImpl(
    private val movieGenreRemoteSource: MovieGenreRemoteSource,
    private val genreLocalSource: GenreLocalSource
) :
    MovieGenreRepository {
    override suspend fun getMovieCategories(): List<Genre>? {
        var genreList: List<Genre> = genreLocalSource.getGenreList()

        if(genreList.isEmpty()){
            genreList = movieGenreRemoteSource.getCategoryMovies().genres
            genreLocalSource.insertGenreList(genreList = genreList)
        }

        return genreList
    }
}