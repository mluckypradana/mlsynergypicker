package com.luc.mobilelegends

import android.app.Application
import com.google.android.gms.ads.MobileAds
import com.luc.mobilelegends.core.BaseViewModel
import com.luc.mobilelegends.repo.HeroRepo
import com.luc.mobilelegends.repo.TypeRepo
import com.luc.mobilelegends.ui.vm.MainVm
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
        single { TypeRepo() }
        single { HeroRepo() }
    }

    override fun onCreate() {
        if (GlobalContext.getOrNull() == null)
            startKoin {
                androidContext(this@App)
                modules(vmModule)
                modules(repoModule)
            }

        MobileAds.initialize(this)
        super.onCreate()
    }
}