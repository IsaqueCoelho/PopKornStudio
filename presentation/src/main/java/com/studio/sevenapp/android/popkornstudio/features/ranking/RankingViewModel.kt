package com.studio.sevenapp.android.popkornstudio.features.ranking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.studio.sevenapp.android.domain.model.Genre
import com.studio.sevenapp.android.domain.model.User
import com.studio.sevenapp.android.domain.moviegenre.MovieGenreUseCase
import com.studio.sevenapp.android.popkornstudio.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RankingViewModel(
    private val movieCategory: MovieGenreUseCase
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
        val userList = listOf(
            User(
                name = "Test1",
                pictureUrl = "https://lh3.googleusercontent.com/ogw/ADGmqu9ay20pUmjnXzLiLvZGfzGFZST5naDaZIO17UBF=s83-c-mo",
                level = 7
            ),
            User(
                name = "Test1",
                pictureUrl = "https://static3.tcdn.com.br/img/img_prod/460977/guarda_chuva_katana_samurai_42776_1_20190411193854.jpg",
                level = 3
            ),
            User(
                name = "Test1",
                pictureUrl = "https://2.bp.blogspot.com/-pkq1t0htRqc/VvMZ0rhAc8I/AAAAAAAAy6U/ebmTX_8hHHQC18KiZ5HS5rbD8SPV0pdFA/s1600/que%2Bdeselegante.jpg",
                level = 1
            )
        )

        delay(3000)
        rankingListLv.postValue(userList)
    }
}