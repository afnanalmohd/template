package com.example.template.common.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.template.common.domain.usecase.LoginUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers


class LoginViewModel(private val loginUseCase: LoginUseCase) : ViewModel() {
    private val _loginResponse = MutableLiveData<String>()
    val loginResponse: LiveData<String> get() = _loginResponse

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val disposable = CompositeDisposable()

    fun login(email: String, password: String) {
        disposable.add(
            loginUseCase.execute(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { token -> _loginResponse.postValue(token) },
                    { throwable -> _error.postValue(throwable.message) }
                )
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
