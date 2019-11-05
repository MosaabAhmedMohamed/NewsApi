package com.example.medicatask.di

import androidx.lifecycle.ViewModelProvider
import com.example.medicatask.viewmodels.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(factory : ViewModelProviderFactory):ViewModelProvider.Factory
}