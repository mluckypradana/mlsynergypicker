package com.luc.mlbbrs.ui.holder

import androidx.core.content.ContextCompat
import androidx.databinding.ObservableField
import com.luc.mlbbrs.data.Type
import com.luc.mlbbrs.extension.getAppContext

class TypeHolder(val data: Type) : ColorHolder() {
    var selected: ObservableField<Boolean> = ObservableField(false)

    fun getColor(): Int = ContextCompat.getColor(getAppContext(), getTypeColor(data.id))
}