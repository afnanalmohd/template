package com.example.template.common.data.model

data class UserResponse(
    val data: UserData
)

data class UserData(
    val id: Int,
    val email: String,
    val first_name: String,
    val last_name: String,
    val avatar: String
)
