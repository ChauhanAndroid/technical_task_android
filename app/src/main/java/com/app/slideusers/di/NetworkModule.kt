package com.app.slideusers.di

import com.app.slideusers.data.remote.network.ApiService
import com.app.slideusers.data.remote.network.HeaderInterceptor
import com.app.slideusers.domain.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesHeaderInterceptor() = HeaderInterceptor()

    @Singleton
    @Provides
    fun providesHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        headerInterceptor: HeaderInterceptor
    ): OkHttpClient {
        return OkHttpClient
            .Builder().apply {
                //add headers at one place
                addInterceptor(
                    headerInterceptor
                )
                //debug logging interceptor
                addInterceptor(
                    httpLoggingInterceptor
                )
            }
            .readTimeout(Constants.NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(Constants.NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }


    @Singleton
    @Provides
    fun providesRetrofit(
        okHttpClient: OkHttpClient, scallarConverterFactory: ScalarsConverterFactory,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.DOMAIN_URL).client(okHttpClient)
            .addConverterFactory(
                scallarConverterFactory
            ).addConverterFactory(gsonConverterFactory).build()
    }

    @Singleton
    @Provides
    fun provideAPIService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun providesConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun providesScalarFactory(): ScalarsConverterFactory {
        return ScalarsConverterFactory.create()
    }
}