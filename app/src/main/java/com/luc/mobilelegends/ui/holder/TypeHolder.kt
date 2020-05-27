package com.luc.mobilelegends.ui.holder

import androidx.core.content.ContextCompat
import androidx.databinding.ObservableField
import com.luc.mobilelegends.R
import com.luc.mobilelegends.data.*
import com.luc.mobilelegends.extension.getAppContext

class TypeHolder(val data: Type) {
    var selected: ObservableField<Boolean> = ObservableField(false)

    fun getColor(): Int {
        return ContextCompat.getColor(
            getAppContext(), when (data.id) {
                WEAPON -> R.color.bg_weapon
                TARGEMAN -> R.color.bg_targeman
                MARKSMAN -> R.color.bg_marksman
                ELEMENTALIST -> R.color.bg_elementalist
                MAGE -> R.color.bg_mage
                GUARDIAN -> R.color.bg_guardian
                SUMMONER -> R.color.bg_summoner
                WRESTLER -> R.color.bg_wrestler
                ASSASSIN -> R.color.bg_assassins
                ELF -> R.color.bg_elf
                DESERT -> R.color.bg_desert
                ERUDITIO -> R.color.bg_eruditio
                CYBORG -> R.color.bg_cyborg
                DEMON -> R.color.bg_demon
                DRAGON -> R.color.bg_dragon
                MONASTERY -> R.color.bg_monastery
                NORTH -> R.color.bg_north
                ABYSS -> R.color.bg_abyss
                CELESTIAL -> R.color.bg_celestial
                EMPIRE -> R.color.bg_empire
                else -> R.color.bg_black
            }
        )
    }
}