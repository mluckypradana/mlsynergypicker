package com.luc.mlbbrs.repo

import com.luc.mlbbrs.core.CoreRepo
import com.orhanobut.hawk.Hawk

class RankRepo() : CoreRepo() {
    val ranks: List<String> = listOf(
        "Warrior", "Elite", "Master", "Grandmaster", "Epic", "Legend", "Mythic", "Mythical Glory"
    )
    val tiers: List<String> = listOf("I", "II", "III", "IV", "V")
    var resetDay = 100
    var maxStar = 100
    var maxTier = 5
    var maxPower = ((ranks.size - 1) * maxTier * maxStar)

    fun saveStartRankDate(millis: Long) = Hawk.put("START_RANK_DATE", millis)

    fun saveStartPower(power: Int) = Hawk.put("START_POWER", power)

    fun getStartRankDate(): Long = Hawk.get("START_RANK_DATE", 0L)

    fun getStartPower(): Int = Hawk.get("START_POWER", 0)

    fun getRank(power: Int): String {
        var divisionIndex = power / maxTier / maxStar
        if (divisionIndex >= ranks.size) divisionIndex = 0

        val division = ranks[divisionIndex]
        val removedPower = divisionIndex * maxTier * maxStar
        val tierPower = power - removedPower
        var tierIndex = maxTier - (tierPower / maxStar) - 1
        if (tierIndex < 0) tierIndex = 0
        val tier = tiers[tierIndex]
        val star = (power - removedPower).rem(maxStar) + 1
        return "$division $tier, $star"
    }
}