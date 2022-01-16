package com.app.slideusers.presentation.ui.feature_user_list.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.slideusers.data.remote.network.NetworkResult
import com.app.slideusers.domain.use_cases.UserUseCase
import com.app.slideusers.domain.utils.Constants.LAST_PAGE
import com.app.slideusers.domain.utils.Constants.LIMIT
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val userUseCase: UserUseCase) : ViewModel() {

   private val _responseUserList: MutableLiveData<UserListState> = MutableLiveData()
     val responseUserList: LiveData<UserListState> = _responseUserList

     fun getUserList() {
        userUseCase.invoke(constructQuery()).onEach { result->
            when(result){
                is NetworkResult.Loading->{
                Log.e("loading","true")
                    _responseUserList.value = UserListState(true)
                }

                is NetworkResult.Success->{
                    Log.e("Success",result.data.toString())
                    _responseUserList.value = result.data?.let { UserListState(userList = it) }
                }

                is NetworkResult.Error->{
                    _responseUserList.value = UserListState(error = result.message)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun addNewUser(name:String,email:String) {
        userUseCase.invoke("add",constructUser(name=name,email=email)).onEach { result->
            when(result){
                is NetworkResult.Loading->{
                    Log.e("loading","true")
                }

                is NetworkResult.Success->{
                    Log.e("Success",result.data.toString())
                }

                is NetworkResult.Error->{
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun constructUser(name:String,email:String):HashMap<String,String>{
       val user = HashMap<String,String>()
        user["name"]= name
        user["email"]= email
        return user
    }

    private fun constructQuery():HashMap<String,Int>{
        val query = HashMap<String,Int>()
        query["page"]= LAST_PAGE
        query["limit"]= LIMIT
        return query
    }

    fun deleteUser(userId:String) {
        userUseCase.invoke(userId).onEach { result->
            when(result){
                is NetworkResult.Loading->{
                    Log.e("loading","true")
                }

                is NetworkResult.Success->{
                    Log.e("Success",result.data.toString())
                }

                is NetworkResult.Error->{
                }
            }
        }.launchIn(viewModelScope)
    }


}