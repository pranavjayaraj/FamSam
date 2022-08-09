package com.pranavjayaraj.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pranavjayaraj.common.ViewModelFactory
import com.pranavjayaraj.common.ViewModelKey
import com.pranavjayaraj.ui.viewModels.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun providesMainViewModel(viewModel: HomeViewModel): ViewModel
}