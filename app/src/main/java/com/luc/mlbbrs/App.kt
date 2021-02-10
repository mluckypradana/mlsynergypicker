package com.luc.mlbbrs

import android.app.Application
import com.luc.mlbbrs.core.BaseViewModel
import com.luc.mlbbrs.repo.RankRepo
import com.luc.mlbbrs.ui.vm.MainVm
import com.orhanobut.hawk.Hawk
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {
    private val vmModule = module {
        viewModel { BaseViewModel(androidApplication()) }
        viewModel { MainVm(androidApplication()) }
    }
    private val repoModule = module {
        single { RankRepo() }
    }

    override fun onCreate() {
        Hawk.init(this).build()

        if (GlobalContext.getOrNull() == null)
            startKoin {
                androidContext(this@App)
                modules(vmModule)
                modules(repoModule)
            }

        super.onCreate()
    }
}