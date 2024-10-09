package com.example.yiuyiuyoyoho_comp304sec003_lab02

import android.app.Application
import com.example.yiuyiuyoyoho_comp304sec003_lab02.di.appModules
import org.koin.core.context.startKoin

class TaskApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModules)
        }
    }
}