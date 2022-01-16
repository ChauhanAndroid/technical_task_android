package com.app.slideusers.data.remote.dto.userlist

import com.app.slideusers.domain.model.UserModel

data class Data(
    val email: String,
    val gender: String,
    val id: Int,
    val name: String,
    val status: String
)
fun Data.toUserModel():UserModel{
    return UserModel(
        id = id,
        email= email,
        name = name
    )
}