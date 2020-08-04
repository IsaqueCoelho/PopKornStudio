package com.studio.sevenapp.android.popkornstudio.features.game.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.studio.sevenapp.android.domain.model.Genre
import com.studio.sevenapp.android.domain.moviegenre.MovieGenreUseCase
import com.studio.sevenapp.android.popkornstudio.R
import com.studio.sevenapp.android.popkornstudio.base.BaseViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class GameCategoryViewModel(
    private val movieCategory: MovieGenreUseCase
) : BaseViewModel() {

    private val scope = CoroutineScope(SupervisorJob())
    private val handler = CoroutineExceptionHandler { _, _ ->
        categoryListLv.postValue(emptyList())
        mustShowToastLv.postValue(Pair(first = true, second = R.string.failure_load_category))
    }

    private val categoryListLv = MutableLiveData<List<Genre>>()
    fun showCategory(): LiveData<List<Genre>> = categoryListLv

    init {
        loadStateLv.postValue(true)
        scope.launch(handler) {
            getCategories()
        }
    }

    private suspend fun getCategories() {
        val categoryListResult = movieCategory.getMovieCategories()
        categoryListLv.postValue(categoryListResult)
    }
}