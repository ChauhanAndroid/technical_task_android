package com.app.slideusers.domain.repository

import com.app.slideusers.data.remote.dto.userlist.Data
import com.app.slideusers.data.remote.dto.userlist.UserList

interface UserListRepository {

    suspend fun getUserList(): List<Data>

    suspend fun addNewUser(inputBody:HashMap<String,String>): String

    suspend fun deleteUser(userId:String): String

}