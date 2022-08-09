package com.pranavjayaraj.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Binds

@Module
abstract class AppModule{
    @Binds
    abstract fun bindContext(application: Application): Context

}