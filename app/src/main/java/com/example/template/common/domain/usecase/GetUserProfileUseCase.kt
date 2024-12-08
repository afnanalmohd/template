package com.example.template.common.domain.usecase

import com.example.template.common.data.model.UserData
import com.example.template.common.data.repository.UserRepository
import io.reactivex.rxjava3.core.Single


class GetUserProfileUseCase(private val userRepository: UserRepository) {
    fun execute(userId: String): Single<UserData> {
        return userRepository.getUserProfile(userId).map { it.data }
    }
}

