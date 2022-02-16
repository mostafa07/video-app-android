package com.example.android.videoapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.videoapp.data.model.domain.User

class LoginViewModel : ViewModel() {

    private val _user: MutableLiveData<User> = MutableLiveData(User("", ""))
    var user: LiveData<User>
        get() = _user
        set(value) {
            _user.value = value.value
        }

    private val _hasUserLoggedIn: MutableLiveData<Boolean> = MutableLiveData()
    val hasUserLoggedIn: LiveData<Boolean>
        get() = _hasUserLoggedIn

    private val _isContentLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isContentLoading: LiveData<Boolean>
        get() = _isContentLoading


    fun login() {
        showLoading()
        val isCredentialsValid = validateLogin()
        _hasUserLoggedIn.value = isCredentialsValid
        hideLoading()
    }

    // Note: No real validation is done here; It is only for checking whether the input is empty or not
    private fun validateLogin(): Boolean {
        val email = _user.value?.email
        val password = _user.value?.password
        return !(email == null || password == null
                || email.trim().isEmpty()
                || password.trim().isEmpty())
    }


    private fun showLoading() {
        _isContentLoading.value = true
    }

    private fun hideLoading() {
        _isContentLoading.value = false
    }
}