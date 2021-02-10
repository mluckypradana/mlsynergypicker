package com.luc.mlbbrs.ui.holder

import androidx.core.content.ContextCompat
import androidx.databinding.ObservableField
import com.luc.mlbbrs.R
import com.luc.mlbbrs.data.Hero
import com.luc.mlbbrs.extension.getAppContext

class HeroHolder(val data: Hero){
    var picked: ObservableField<Boolean> = ObservableField(false)

    fun getColor(): Int {
        return ContextCompat.getColor(
            getAppContext(), when (data.star) {
                5-> R.color.bg_5_star
                4-> R.color.bg_4_star
                3-> R.color.bg_3_star
                2-> R.color.bg_2_star
                else -> R.color.bg_1_star
            }
        )
    }
}