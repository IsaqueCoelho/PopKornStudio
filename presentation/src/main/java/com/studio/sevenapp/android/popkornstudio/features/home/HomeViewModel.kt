package com.studio.sevenapp.android.popkornstudio.features.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.studio.sevenapp.android.domain.user.UserUseCase
import com.studio.sevenapp.android.popkornstudio.base.BaseViewModel
import kotlinx.coroutines.launch

class HomeViewModel(
    private val userUseCase: UserUseCase
) : BaseViewModel() {

    private val mustShowUserLv = MutableLiveData<FirebaseUser>()
    fun showUser() : LiveData<FirebaseUser> = mustShowUserLv

    init {
        viewModelScope.launch {
            loadData()
        }
    }

    private fun loadData() {
        mustShowUserLv.postValue(
            userUseCase.getCurrentUser()
        )
    }
}