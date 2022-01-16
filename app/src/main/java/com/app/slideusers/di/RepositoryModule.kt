package com.app.slideusers.di

import com.app.slideusers.data.remote.network.ApiService
import com.app.slideusers.data.repository.UserRepositoryImp
import com.app.slideusers.domain.repository.UserListRepository
import com.app.slideusers.domain.use_cases.UserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesUserListRepository(apiService: ApiService):UserListRepository{
        return UserRepositoryImp(apiService)
    }


    @Singleton
    @Provides
    fun  providesGetUserListUSeCase(userListRepository: UserListRepository):UserUseCase{
        return UserUseCase(userListRepository)
    }


}