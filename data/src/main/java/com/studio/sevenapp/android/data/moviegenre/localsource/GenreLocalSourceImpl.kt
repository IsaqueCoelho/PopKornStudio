package com.studio.sevenapp.android.data.moviegenre.localsource

import com.studio.sevenapp.android.data.mapper.GenreMapper
import com.studio.sevenapp.android.data.model.GenreEntity
import com.studio.sevenapp.android.domain.model.Genre

class GenreLocalSourceImpl(
    private val genreDao: GenreDao,
    private val genreMapper: GenreMapper
) : GenreLocalSource {

    override suspend fun insertGenreList(genreList: List<Genre>) {
        val genreEntityList: List<GenreEntity> =
            genreMapper.transformListToEntity(objectList = genreList)

        genreDao.insertGenreList(genreEntityList = genreEntityList)
    }

    override suspend fun getGenreList(): List<Genre> {
        return genreMapper.transformListFromEntity(entityList = genreDao.getGenreList())
    }
}