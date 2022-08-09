package com.pranavjayaraj.di

import com.pranavjayaraj.data.services.ApiRepositoryImpl
import com.pranavjayaraj.data.services.NetworkModule
import com.pranavjayaraj.data.SchedulersFacade
import com.pranavjayaraj.domain.SchedulerProvider
import com.pranavjayaraj.domain.repository.ApiRepository
import com.pranavjayaraj.domain.repository.DbRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun provideApiRepository(apiRepositoryImpl: ApiRepositoryImpl): ApiRepository

    @Binds
    @Singleton
    abstract fun provideSchedulerProvider(schedulersFacade: SchedulersFacade): SchedulerProvider

}