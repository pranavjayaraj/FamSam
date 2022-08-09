package com.pranavjayaraj.di

import android.app.Application
import com.pranavjayaraj.base.FamSampleApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    DataModule::class,
    AppModule::class,
    ActivityBindingModule::class,
    AndroidSupportInjectionModule::class,
    AndroidInjectionModule::class])
interface AppComponent : AndroidInjector<FamSampleApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): AppComponent.Builder

        fun build(): AppComponent
    }
}