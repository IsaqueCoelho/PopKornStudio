package com.studio.sevenapp.android.domain.base

abstract class BaseMapper<DATA_CLASS, ENTITY_CLASS> {

    abstract fun transformToEntity(dataObject: DATA_CLASS): ENTITY_CLASS

    abstract fun transformFromEntity(entityObject: ENTITY_CLASS): DATA_CLASS

    fun transformListFromEntity(entityList: List<ENTITY_CLASS>): List<DATA_CLASS> {
        return entityList.map(::transformFromEntity)
    }

    fun transformListToEntity(objectList: List<DATA_CLASS>): List<ENTITY_CLASS> {
        return objectList.map(::transformToEntity)
    }
}