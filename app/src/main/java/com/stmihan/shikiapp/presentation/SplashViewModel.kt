package com.stmihan.shikiapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stmihan.shikiapp.domain.SplashRepository
import kotlinx.coroutines.launch

class SplashViewModel(
    private val splashRepository: SplashRepository
) : ViewModel() {

    private var _loggedIn = MutableLiveData<Boolean>()
    val loggedIn: LiveData<Boolean> = _loggedIn

    fun tryAuth() {
        viewModelScope.launch {
            _loggedIn.postValue(splashRepository.tryLogin())
        }
    }
}