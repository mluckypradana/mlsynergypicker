package com.luc.mobilelegends.ui.holder

import androidx.core.content.ContextCompat
import androidx.databinding.ObservableField
import com.luc.mobilelegends.R
import com.luc.mobilelegends.data.*
import com.luc.mobilelegends.extension.getAppContext

class TypeHolder(val data: Type) : ColorHolder() {
    var selected: ObservableField<Boolean> = ObservableField(false)

    fun getColor(): Int = ContextCompat.getColor(getAppContext(), getTypeColor(data.id))
}