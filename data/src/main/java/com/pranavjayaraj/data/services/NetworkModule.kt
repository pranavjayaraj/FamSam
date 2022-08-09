package com.pranavjayaraj.data.services

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import com.google.gson.Gson
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import com.pranavjayaraj.data.BuildConfig
import com.pranavjayaraj.data.utils.HeaderInterceptor
import javax.inject.Singleton

@Module
class NetworkModule {
    private var rxAdapterFactory = RxJava2CallAdapterFactory.create()

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient()
    }

    @Provides
    fun provideOkHttpBuilder(
        okHttp: OkHttpClient
    ): OkHttpClient.Builder {
        val okHttpBuilder = okHttp.newBuilder()
        okHttpBuilder.addInterceptor(HeaderInterceptor())
        okHttpBuilder.readTimeout(40, TimeUnit.SECONDS)
        okHttpBuilder.connectTimeout(40, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            okHttpBuilder.addNetworkInterceptor(StethoInterceptor())
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            okHttpBuilder.addInterceptor(loggingInterceptor)
        }
        return okHttpBuilder
    }


    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }


    @Provides
    @Singleton
    @Named("auth_retrofit")
    fun provideRetrofit(okHttpBuilder: OkHttpClient.Builder, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Urls.BASE_URL)
            .client(okHttpBuilder.build())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addCallAdapterFactory(rxAdapterFactory)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    }


    @Provides
    @Named("retrofit")
    fun provideAuthRetrofit(okHttp: OkHttpClient, gson: Gson): Retrofit {
        val httpClientBuilder = okHttp.newBuilder()
        httpClientBuilder.connectTimeout(40, TimeUnit.SECONDS)
        httpClientBuilder.readTimeout(40, TimeUnit.SECONDS)
        httpClientBuilder.writeTimeout(40, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            httpClientBuilder.addNetworkInterceptor(StethoInterceptor())
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            httpClientBuilder.addInterceptor(loggingInterceptor)
        }
        return Retrofit.Builder()
            .baseUrl(Urls.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(rxAdapterFactory)
            .client(httpClientBuilder.build())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(@Named("auth_retrofit") retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

}