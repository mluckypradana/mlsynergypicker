package com.luc.mlbbrs.ui.holder

import com.luc.mlbbrs.R
import com.luc.mlbbrs.data.*

open class ColorHolder {

    protected fun getTypeColor(typeId: Int): Int = when (typeId) {
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
}