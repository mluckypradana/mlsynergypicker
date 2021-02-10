package com.luc.mlbbrs.ui.holder

import androidx.core.content.ContextCompat
import com.luc.mlbbrs.data.Type
import com.luc.mlbbrs.extension.getAppContext

class SynergyHolder(val type: Type, val total: Int) : ColorHolder() {
    fun getColor(): Int = ContextCompat.getColor(getAppContext(), getTypeColor(type.id))
}