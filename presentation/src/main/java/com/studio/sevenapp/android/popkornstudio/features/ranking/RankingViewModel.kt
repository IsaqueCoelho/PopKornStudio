package com.studio.sevenapp.android.popkornstudio.features.ranking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.studio.sevenapp.android.domain.model.Genre
import com.studio.sevenapp.android.domain.model.User
import com.studio.sevenapp.android.domain.moviegenre.MovieGenreUseCase
import com.studio.sevenapp.android.domain.user.UserUseCase
import com.studio.sevenapp.android.popkornstudio.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RankingViewModel(
    private val movieCategory: MovieGenreUseCase,
    private val userUseCase: UserUseCase
) : BaseViewModel(){

    private val categoryListLv = MutableLiveData<List<Genre>>()
    private val rankingListLv = MutableLiveData<List<User>>()

    fun showCategory(): LiveData<List<Genre>> = categoryListLv
    fun showRanking(): LiveData<List<User>> = rankingListLv

    init {
        loadStateLv.postValue(true)
        viewModelScope.launch {
            getCategories()
        }
    }

    fun getRanking(genre: Genre){
        viewModelScope.launch {
            loadStateLv.postValue(true)
            getUserRanking(genre = genre)
        }
    }

    private suspend fun getCategories() {
        val categoryListResult = movieCategory.getMovieCategories()
        categoryListLv.postValue(categoryListResult)
        getUserRanking(genre = categoryListResult[0])
    }

    private suspend fun getUserRanking(genre: Genre){
        val userRanking = userUseCase.getRankingByGenre(genre = genre)
        rankingListLv.postValue(userRanking)
    }
}