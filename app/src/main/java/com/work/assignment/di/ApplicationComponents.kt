package com.work.assignment.di

import com.work.assignment.retrofit.NetworkModel
import com.work.assignment.activities.HomeActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModel::class, ViewModelModule::class])
interface ApplicationComponents {

    fun inject(homeActivity: HomeActivity)

}