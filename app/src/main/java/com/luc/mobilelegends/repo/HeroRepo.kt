package com.luc.mobilelegends.repo

import com.luc.mobilelegends.core.CoreRepo
import com.luc.mobilelegends.data.*

class HeroRepo() : CoreRepo() {
    val heroes: List<Hero> = listOf(
        Hero("Zilong", 1, listOf(WEAPON, DRAGON), PHYSICAL),
        Hero("Alucard", 3, listOf(WEAPON, MONASTERY), PHYSICAL),
        Hero("Franco", 2, listOf(WEAPON, NORTH), TANK),
        Hero("Freya", 3, listOf(WEAPON, NORTH), PHYSICAL),
        Hero("Argus", 2, listOf(WEAPON, ABYSS), PHYSICAL),
        Hero("Thamuz", 5, listOf(WEAPON, ABYSS), PHYSICAL),
        Hero("Martis", 1, listOf(WEAPON, CELESTIAL), PHYSICAL),
        Hero("Lolita", 2, listOf(TARGEMAN, ELF), PHYSICAL),
        Hero("Johnson", 4, listOf(TARGEMAN, CYBORG), TANK),
        Hero("Baxia", 2, listOf(TARGEMAN, DRAGON), TANK),
        Hero("Tigreal", 3, listOf(TARGEMAN, EMPIRE), TANK),
        Hero("Miya", 1, listOf(MARKSMAN, ELF), PHYSICAL),
        Hero("Irithel", 5, listOf(MARKSMAN, ELF), PHYSICAL),
        Hero("Claude", 4, listOf(MARKSMAN, DESERT, ERUDITIO), PHYSICAL),
        Hero("Bruno", 4, listOf(MARKSMAN, CYBORG), PHYSICAL),
        Hero("Wanwan", 3, listOf(MARKSMAN, DRAGON), PHYSICAL),
        Hero("Popol", 4, listOf(MARKSMAN, NORTH, SUMMONER), PHYSICAL),
        Hero("Moskov", 2, listOf(MARKSMAN, ABYSS), PHYSICAL),
        Hero("Karrie", 3, listOf(MARKSMAN, CELESTIAL), PHYSICAL),
        Hero("Eudora", 2, listOf(ELEMENTALIST, ELF), MAGICAL),
        Hero("Valir", 3, listOf(ELEMENTALIST, DESERT), MAGICAL),
        Hero("Vale", 1, listOf(ELEMENTALIST, DESERT), MAGICAL),
        Hero("Aurora", 4, listOf(ELEMENTALIST, NORTH, MAGE), MAGICAL),
        Hero("Esmeralda", 3, listOf(MAGE, DESERT), MAGICAL),
        Hero("Cecilion", 4, listOf(MAGE, DEMON), MAGICAL),
        Hero("Change", 2, listOf(MAGE, DRAGON), MAGICAL),
        Hero("Alice", 3, listOf(MAGE, ABYSS), MAGICAL),
        Hero("Odette", 5, listOf(MAGE, EMPIRE), MAGICAL),
        Hero("Harith", 1, listOf(MAGE, EMPIRE), MAGICAL),
        Hero("Belerick", 3, listOf(GUARDIAN, ELF), TANK),
        Hero("Minotaur", 5, listOf(GUARDIAN, DESERT), TANK),
        Hero("Diggie", 2, listOf(GUARDIAN, ERUDITIO, SUMMONER), MAGICAL),
        Hero("Akai", 1, listOf(GUARDIAN, DRAGON), TANK),
        Hero("Sun", 4, listOf(SUMMONER, DRAGON), TANK),
        Hero("Zhask", 2, listOf(SUMMONER, CELESTIAL), MAGICAL),
        Hero("Khufra", 3, listOf(WRESTLER, DESERT), TANK),
        Hero("Chou", 1, listOf(WRESTLER, ERUDITIO), PHYSICAL),
        Hero("Jawhead", 2, listOf(WRESTLER, CYBORG), PHYSICAL),
        Hero("Carmila", 2, listOf(WRESTLER, DEMON), TANK),
        Hero("Carmila", 2, listOf(WRESTLER, DEMON), TANK),
        Hero("Dyroth", 3, listOf(WRESTLER, ABYSS), PHYSICAL),
        Hero("Gatotkaca", 5, listOf(WRESTLER, CELESTIAL), TANK),
        Hero("Guinevere", 4, listOf(WRESTLER, EMPIRE), MAGICAL),
        Hero("Karina", 4, listOf(ASSASSIN, ELF), MAGICAL),
        Hero("Selena", 3, listOf(ASSASSIN, ELF), MAGICAL),
        Hero("Saber", 1, listOf(ASSASSIN, CYBORG), PHYSICAL),
        Hero("Ling", 5, listOf(ASSASSIN, DRAGON), PHYSICAL),
        Hero("Natalia", 4, listOf(ASSASSIN, MONASTERY), PHYSICAL),
        Hero("Lancelot", 2, listOf(ASSASSIN, EMPIRE), PHYSICAL),
        Hero("Gusion", 3, listOf(ASSASSIN, EMPIRE), MAGICAL)
    )

    private fun getRawMatchesByType(types: List<Type>): List<Hero> {
        val rawMatches = mutableListOf<Hero>()
        types.forEach { type ->
            val list = heroes.filter { it.types.contains(type.id) }
            rawMatches.addAll(list)
        }
        return rawMatches.sortedBy { it.star }.asReversed()
    }

    fun getMatchesByType(types: List<Type>): List<Hero> = getRawMatchesByType(types).distinct()

    fun getRecommendedMatchesByType(types: List<Type>): List<Hero> {
        val recommendMatches = mutableListOf<Hero>()
        val matches = getRawMatchesByType(types)
        val heroesTotal: Map<Hero, Int> =
            matches.groupingBy { it }.eachCount().filter { it.value > 1 }
        heroesTotal.toList().forEach { recommendMatches.add(it.first) }
        return recommendMatches;
    }
}