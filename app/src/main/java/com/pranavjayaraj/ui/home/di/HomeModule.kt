package com.pranavjayaraj.ui.home.di

import androidx.lifecycle.ViewModelProvider
import com.pranavjayaraj.ui.home.HomeActivity
import com.pranavjayaraj.widget.containerview.viewModels.ContainerViewModel
import dagger.Module
import dagger.Provides

@Module
class HomeModule {
    @Provides
    fun providesHomeViewModel(
        activity: HomeActivity,
        factory: ViewModelProvider.Factory
    ): ContainerViewModel {
        return ViewModelProvider(activity, factory).get(ContainerViewModel::class.java)
    }
}