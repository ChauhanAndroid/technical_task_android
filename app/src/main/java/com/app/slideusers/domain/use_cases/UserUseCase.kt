package com.app.slideusers.domain.use_cases

import android.util.Log
import com.app.slideusers.data.remote.dto.userlist.toUserModel
import com.app.slideusers.data.remote.network.NetworkResult
import com.app.slideusers.domain.model.UserModel
import com.app.slideusers.domain.repository.UserListRepository
import com.app.slideusers.domain.utils.ErrorMessage.Companion.UNKNOWN_ERROR
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.Flow
import javax.inject.Inject

class UserUseCase @Inject constructor(private val userListRepository: UserListRepository) {

    operator fun invoke(queryMap:HashMap<String,Int>) = flow {
        try {
            emit(NetworkResult.Loading())
            val result = userListRepository.getUserList(queryMap).map { it.toUserModel() }
            emit(NetworkResult.Success(result))
        } catch (e: HttpException) {
            Log.e("HttpException", e.localizedMessage)
            emit(NetworkResult.Error(e.localizedMessage ?: UNKNOWN_ERROR))
        } catch (e: IOException) {
            emit(NetworkResult.Error(e.localizedMessage ?: UNKNOWN_ERROR))
        }
    }

    operator fun invoke(tag:String,inputBody:HashMap<String,String>) = flow {
        try {
            emit(NetworkResult.Loading())
            val result = userListRepository.addNewUser(inputBody)
            emit(NetworkResult.Success(result))
        } catch (e: HttpException) {
            emit(NetworkResult.Error(e.localizedMessage ?: UNKNOWN_ERROR))
        } catch (e: IOException) {
            emit(NetworkResult.Error(e.localizedMessage ?: UNKNOWN_ERROR))
        }
    }

    operator fun invoke(userId: String) = flow {
        try {
            emit(NetworkResult.Loading())
            val result = userListRepository.deleteUser(userId)
            emit(NetworkResult.Success(result))
        } catch (e: HttpException) {
            emit(NetworkResult.Error(e.localizedMessage ?: UNKNOWN_ERROR))
        } catch (e: IOException) {
            emit(NetworkResult.Error(e.localizedMessage ?: UNKNOWN_ERROR))

        }
    }

}