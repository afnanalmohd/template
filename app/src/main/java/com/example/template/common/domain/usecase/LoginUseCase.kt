package com.example.template.common.domain.usecase

import com.example.template.common.data.repository.UserRepository
import io.reactivex.rxjava3.core.Single


class LoginUseCase(private val userRepository: UserRepository) {
    fun execute(email: String, password: String): Single<String> {
        return userRepository.login(email, password).map { it.token }
    }
}
