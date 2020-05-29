package com.studio.sevenapp.android.popkornstudio.features.game.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.studio.sevenapp.android.domain.model.MovieGenre
import com.studio.sevenapp.android.domain.moviegenre.MovieGenreUseCase
import com.studio.sevenapp.android.popkornstudio.base.BaseViewModel
import kotlinx.coroutines.launch

class GameCategoryViewModel(
    private val movieCategory: MovieGenreUseCase
) : BaseViewModel(){

    private val categoryListLv = MutableLiveData<List<MovieGenre>>()
    fun showCategory(): LiveData<List<MovieGenre>> = categoryListLv

    init {
        loadStateLv.postValue(true)
        viewModelScope.launch {
            getCategories()
        }
    }

    private suspend fun getCategories() {
        val categoryListResult = movieCategory.getMovieCategories()
        categoryListLv.postValue(categoryListResult)
    }
}