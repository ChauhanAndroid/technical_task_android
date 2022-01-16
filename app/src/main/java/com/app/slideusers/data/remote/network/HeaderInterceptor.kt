package com.app.slideusers.data.remote.network

import com.app.slideusers.domain.utils.Constants
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        builder.header("Authorization", Constants.X_API_KEY)
        return chain.proceed(builder.build())
    }
}