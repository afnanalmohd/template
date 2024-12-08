package com.example.template.common.network


import com.example.template.common.data.model.LoginRequest
import com.example.template.common.data.model.LoginResponse
import com.example.template.common.data.model.UserResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("api/login")
    fun login(@Body loginRequest: LoginRequest): Single<LoginResponse>

    @GET("api/users/{id}")
    fun getUserProfile(@Path("id") userId: String): Single<UserResponse>
}

