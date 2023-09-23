package com.work.assignment.di

import androidx.lifecycle.ViewModel
import com.work.assignment.viewmodel.ImageViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @ClassKey(ImageViewModel::class)
    @IntoMap
    abstract fun imageViewModel(authViewModel: ImageViewModel): ViewModel
}