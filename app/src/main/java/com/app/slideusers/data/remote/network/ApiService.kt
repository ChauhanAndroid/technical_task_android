package com.app.slideusers.data.remote.network

import com.app.slideusers.data.remote.dto.userlist.UserList
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    //methods to call API

    @GET("users")
    suspend fun getUserList():UserList

    @POST("users")
    suspend fun addNewUser(@Body user:HashMap<String,String>):String

    @DELETE("users/{user_id}")
    suspend fun deleteUser(user_id:String):String

}