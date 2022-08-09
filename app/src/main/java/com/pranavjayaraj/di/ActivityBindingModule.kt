package com.pranavjayaraj.di

import com.pranavjayaraj.ui.home.HomeActivity
import com.pranavjayaraj.ui.home.di.HomeModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [ViewModelModule::class])
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [HomeModule::class])
    abstract fun providesMainActivity(): HomeActivity
}
