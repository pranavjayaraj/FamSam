package com.pranavjayaraj.ui.di

import androidx.lifecycle.ViewModelProvider
import com.pranavjayaraj.ui.MainActivity
import com.pranavjayaraj.ui.viewModels.HomeViewModel
import dagger.Module
import dagger.Provides

@Module
class MainModule {
    @Provides
    fun providesHomeViewModel(
        activity: MainActivity,
        factory: ViewModelProvider.Factory
    ): HomeViewModel {
        return ViewModelProvider(activity, factory).get(HomeViewModel::class.java)
    }
}