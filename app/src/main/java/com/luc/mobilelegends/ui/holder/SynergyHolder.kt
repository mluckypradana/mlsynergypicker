package com.luc.mobilelegends.ui.holder

import androidx.core.content.ContextCompat
import androidx.databinding.ObservableField
import com.luc.mobilelegends.R
import com.luc.mobilelegends.data.Hero
import com.luc.mobilelegends.data.Type
import com.luc.mobilelegends.extension.getAppContext

class SynergyHolder(val type: Type, val total: Int) : ColorHolder() {
    fun getColor(): Int = ContextCompat.getColor(getAppContext(), getTypeColor(type.id))
}