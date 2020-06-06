package com.studio.sevenapp.android.data.mapper

import com.studio.sevenapp.android.data.model.GenreEntity
import com.studio.sevenapp.android.domain.base.BaseMapper
import com.studio.sevenapp.android.domain.model.Genre
import java.util.*

class GenreMapper : BaseMapper<Genre, GenreEntity>() {

    override fun transformToEntity(dataObject: Genre): GenreEntity {
        return GenreEntity(
            id = UUID.randomUUID().toString(),
            genreId = dataObject.id,
            name = dataObject.name
        )
    }

    override fun transformFromEntity(entityObject: GenreEntity): Genre {
        return Genre(
            id = entityObject.genreId,
            name = entityObject.name
        )
    }
}