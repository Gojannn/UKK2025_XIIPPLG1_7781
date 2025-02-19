package com.example.ukk2025

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class progressViewModel {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    init {
        _isLoading.value = false
    }

    fun showLoading() {
        _isLoading.value = true
    }

    fun hideLoading() {
        _isLoading.value=false
    }
}