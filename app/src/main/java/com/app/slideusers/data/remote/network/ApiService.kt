package com.app.slideusers.data.remote.network

import com.app.slideusers.data.remote.dto.userlist.UserList
import retrofit2.http.*

interface ApiService {

    //methods to call API

    @GET("users")
    suspend fun getUserList(@QueryMap query:HashMap<String,Int>):UserList

    @POST("users")
    suspend fun addNewUser(@Body user:HashMap<String,String>):String

    @DELETE("users/{user_id}")
    suspend fun deleteUser(user_id:String):String

}