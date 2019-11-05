package com.example.medicatask.di

import com.example.medicatask.di.main.MainFragmentBuilderModule
import com.example.medicatask.di.main.MainModule
import com.example.medicatask.di.main.MainVMModule
import com.example.medicatask.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [MainVMModule::class, MainFragmentBuilderModule::class, MainModule::class])
    abstract fun contributesMainActivity(): MainActivity
}