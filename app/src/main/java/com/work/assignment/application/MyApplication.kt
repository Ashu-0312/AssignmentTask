package com.work.assignment.application

import android.app.Application
import com.work.assignment.di.ApplicationComponents
import com.work.assignment.di.DaggerApplicationComponents


class MyApplication : Application() {
    lateinit var applicationComponent: ApplicationComponents


    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponents.builder().build()

    }

}