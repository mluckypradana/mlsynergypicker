package com.luc.mlbbrs.core

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import org.koin.core.KoinComponent

open class BaseViewModel(application: Application) : AndroidViewModel(application), KoinComponent
