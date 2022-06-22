package com.stmihan.feature_main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stmihan.core.domain.models.User
import com.stmihan.feature_main.data.MainRepository
import kotlinx.coroutines.launch

class MainViewModel(
    private val mainRepository: MainRepository
) : ViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    fun getUser() {
        viewModelScope.launch {
            _user.postValue(mainRepository.getMe())
        }
    }
}