package com.app.slideusers.presentation.ui.feature_user_list.viewmodels

import com.app.slideusers.domain.model.UserModel
import java.lang.Error

data class UserListState(
    val isLoading: Boolean = false,
    val userList: List<UserModel> = listOf(),
    val error: String? = "Something Went Wrong"
)
