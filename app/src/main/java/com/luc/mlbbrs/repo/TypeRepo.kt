package com.luc.mlbbrs.repo

import com.luc.mlbbrs.core.CoreRepo
import com.luc.mlbbrs.data.*

class TypeRepo() : CoreRepo() {
    val list: MutableList<Type> = mutableListOf()

    fun getTypes(): List<Type> {
        list.add(Type(WEAPON, "Weapon Master"))
        list.add(Type(TARGEMAN, "Targeman"))
        list.add(Type(MARKSMAN, "Marksman"))
        list.add(Type(ELEMENTALIST, "Elementalist"))
        list.add(Type(GUARDIAN, "Guardian"))
        list.add(Type(SUMMONER, "Summoner"))
        list.add(Type(WRESTLER, "Wrestler"))
        list.add(Type(ASSASSIN, "Assassin"))
        list.add(Type(ELF, "Elf"))
        list.add(Type(DESERT, "Desert"))
        list.add(Type(ERUDITIO, "Eruditio"))
        list.add(Type(CYBORG, "Cyborg"))
        list.add(Type(DEMON, "Demon"))
        list.add(Type(DRAGON, "Dragon"))
        list.add(Type(MONASTERY, "Monastery"))
        list.add(Type(MAGE, "Mage"))
        list.add(Type(NORTH, "Northen Vale"))
        list.add(Type(ABYSS, "Abyss"))
        list.add(Type(CELESTIAL, "Celestial"))
        list.add(Type(EMPIRE, "Empire"))
        return list.sortedBy { it.name }
    }
}