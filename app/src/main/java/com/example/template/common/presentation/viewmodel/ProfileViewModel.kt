package com.example.template.common.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.template.common.data.model.UserData
import com.example.template.common.domain.usecase.GetUserProfileUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers


class ProfileViewModel(private val getUserProfileUseCase: GetUserProfileUseCase) : ViewModel() {
    private val _userResponse = MutableLiveData<UserData>()
    val userResponse: LiveData<UserData> get() = _userResponse

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val disposable = CompositeDisposable()

    fun getUserProfile(userId: String) {
        disposable.add(
            getUserProfileUseCase.execute(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { userData -> _userResponse.postValue(userData) },
                    { throwable -> _error.postValue(throwable.message) }
                )
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
