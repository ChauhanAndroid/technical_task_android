package com.app.slideusers.data.repository

import com.app.slideusers.data.remote.dto.userlist.Data
import com.app.slideusers.data.remote.dto.userlist.UserList
import com.app.slideusers.data.remote.network.ApiService
import com.app.slideusers.domain.repository.UserListRepository
import javax.inject.Inject

class UserRepositoryImp @Inject constructor(private val apiService: ApiService) : UserListRepository {
    override suspend fun getUserList(query:HashMap<String,Int>): List<Data> {
        return apiService.getUserList(query=query).data
    }

    override suspend fun addNewUser(inputBody: HashMap<String, String>): String {
        return apiService.addNewUser(inputBody)
    }

    override suspend fun deleteUser(userId:String): String {
        return apiService.deleteUser(userId)
    }
}