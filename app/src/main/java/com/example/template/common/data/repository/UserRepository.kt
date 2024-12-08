package com.example.template.common.data.repository

import com.example.template.common.data.model.LoginRequest
import com.example.template.common.data.model.LoginResponse
import com.example.template.common.data.model.UserResponse
import com.example.template.common.network.ApiService
import io.reactivex.rxjava3.core.Single


class UserRepository(private val apiService: ApiService) {
    fun login(email: String, password: String): Single<LoginResponse> {
        return apiService.login(LoginRequest(email, password))
    }

    fun getUserProfile(userId: String): Single<UserResponse> {
        return apiService.getUserProfile(userId)
    }
}
