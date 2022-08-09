package com.pranavjayaraj.data.utils

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor(): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        var authToken = ""
        if(!authToken.isNullOrEmpty()) {
            request = request.newBuilder()
                .addHeader( "Authorization", "OAuth".plus(" $authToken"))
                .addHeader("Content-Type", "application/json")
                .addHeader("OSName", "android")
                .build()
        }
        return chain.proceed(request)
    }
}