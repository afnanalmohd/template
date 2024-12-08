package com.example.template.common.di


import com.example.template.common.data.repository.UserRepository
import com.example.template.common.domain.usecase.GetUserProfileUseCase
import com.example.template.common.domain.usecase.LoginUseCase
import com.example.template.common.network.ApiService
import com.example.template.common.presentation.viewmodel.LoginViewModel
import com.example.template.common.presentation.viewmodel.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://reqres.in/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    single { UserRepository(get()) }

    factory { LoginUseCase(get()) }
    factory { GetUserProfileUseCase(get()) }

    viewModel { LoginViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
}
