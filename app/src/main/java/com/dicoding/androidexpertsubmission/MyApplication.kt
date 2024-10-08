package com.dicoding.androidexpertsubmission

import android.app.Application
import com.dicoding.androidexpertsubmission.core.di.moduleDatabase
import com.dicoding.androidexpertsubmission.core.di.moduleNetwork
import com.dicoding.androidexpertsubmission.core.di.moduleRepository
import com.dicoding.androidexpertsubmission.di.useCaseModule
import com.dicoding.androidexpertsubmission.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    moduleDatabase,
                    moduleNetwork,
                    moduleRepository,
                    useCaseModule,
                    viewModelModule,
                )
            )
        }
    }
}