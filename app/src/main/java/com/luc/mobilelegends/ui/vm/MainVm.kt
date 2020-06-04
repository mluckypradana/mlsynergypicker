package com.luc.mobilelegends.ui.vm

import android.app.Application
import com.luc.mobilelegends.R
import com.luc.mobilelegends.core.BaseListAdapter
import com.luc.mobilelegends.core.BaseViewModel
import com.luc.mobilelegends.data.*
import com.luc.mobilelegends.repo.HeroRepo
import com.luc.mobilelegends.repo.TypeRepo
import com.luc.mobilelegends.ui.holder.HeroHolder
import com.luc.mobilelegends.ui.holder.ItemHolder
import com.luc.mobilelegends.ui.holder.SynergyHolder
import com.luc.mobilelegends.ui.holder.TypeHolder
import org.koin.core.inject

class MainVm(context: Application) : BaseViewModel(context) {
    private lateinit var recommendedHolders: MutableList<HeroHolder>
    private lateinit var typeHolders: MutableList<TypeHolder>
    private val types: List<Type>
    val heroes: MutableList<Hero> = mutableListOf()
    val selectedHeroes: MutableList<Hero> = mutableListOf()
    val recommendedHeroes: MutableList<Hero> = mutableListOf()
    val synergyHolders: MutableList<SynergyHolder> = mutableListOf()
    val itemHolders: MutableList<ItemHolder> = mutableListOf()
    private val selectedTypes: MutableList<Type> = mutableListOf()
    private val typeRepo: TypeRepo by inject()
    private val heroRepo: HeroRepo by inject()
    val typeAdapter: BaseListAdapter = BaseListAdapter(R.layout.item_type)
    val heroAdapter: BaseListAdapter = BaseListAdapter(R.layout.item_hero)
    val synergyAdapter: BaseListAdapter = BaseListAdapter(R.layout.item_synergy)
    val recommendedAdapter: BaseListAdapter = BaseListAdapter(R.layout.item_hero)
    val itemAdapter: BaseListAdapter = BaseListAdapter(R.layout.item_item)

    init {
        types = typeRepo.getTypes()
        initTypeAdapter()
        initHeroAdapter()
        initSynergyAdapter()
        initItemAdapter()
    }

    private fun initSynergyAdapter() {
        synergyAdapter.items = synergyHolders
    }

    private fun initItemAdapter() {
        itemAdapter.items = itemHolders
    }

    private fun initTypeAdapter() {
        typeHolders = mutableListOf()
        types.forEach { typeHolders.add(TypeHolder(it)) }
        typeAdapter.items = typeHolders
        typeAdapter.onItemClick = { _, position ->
            val selected = typeHolders[position].selected.get() ?: false
            if (!selected)
                selectedTypes.add(typeHolders[position].data)
            else
                selectedTypes.remove(typeHolders[position].data)
            updateFilter()
            typeHolders[position].selected.set(!selected)
        }
    }

    private fun initHeroAdapter() {
        heroAdapter.items = getHeroHolders(heroes)

        recommendedHolders = getHeroHolders(recommendedHeroes)
        recommendedAdapter.items = recommendedHolders
        recommendedAdapter.onItemClick = { _, position ->
            val selected = recommendedHolders[position].picked.get() ?: false
            recommendedHolders[position].picked.set(!selected)
            selectedHeroes.add(recommendedHeroes[position])
        }
    }

    private fun updateFilter() {
        recommendedHeroes.clear()
        if (selectedTypes.isNotEmpty())
            recommendedHeroes.addAll(heroRepo.getRecommendedMatchesByType(selectedTypes))
        recommendedHolders = getHeroHolders(recommendedHeroes)
        recommendedAdapter.items = recommendedHolders
        recommendedAdapter.notifyDataSetChanged()

        val recommendedTypes = mutableListOf<Int>()
        recommendedHeroes.forEach { recommendedTypes.addAll(it.types) }

        synergyHolders.clear()
        val heroesTotal: Map<Int, Int> =
            recommendedTypes.groupingBy { it }.eachCount().filter { it.value > 1 }
        heroesTotal.toList().forEach { heroTotal ->
            synergyHolders.add(
                SynergyHolder(
                    types.find { it.id == heroTotal.first }!!,
                    heroTotal.second
                )
            )
        }
        synergyAdapter.items = synergyHolders
        synergyAdapter.notifyDataSetChanged()

        heroes.clear()
        if (selectedTypes.isNotEmpty())
            heroes.addAll(heroRepo.getMatchesByType(selectedTypes))
        heroes.removeAll(recommendedHeroes)
        heroAdapter.items = getHeroHolders(heroes)
        heroAdapter.notifyDataSetChanged()

        var physicalTotal = 0
        var magicalTotal = 0
        var tankTotal = 0
        recommendedHeroes.forEach {
            when (it.item) {
                PHYSICAL -> physicalTotal++
                MAGICAL -> magicalTotal++
                TANK -> tankTotal++
            }
        }
        itemHolders.clear()
        if (physicalTotal > 0)
            itemHolders.add(ItemHolder("Physical Item", physicalTotal))
        if (magicalTotal > 0)
            itemHolders.add(ItemHolder("Magical Item", magicalTotal))
        if (tankTotal > 0)
            itemHolders.add(ItemHolder("Tank Item", tankTotal))

        itemAdapter.items = itemHolders
        itemAdapter.notifyDataSetChanged()
    }

    fun getHeroHolders(heroes: MutableList<Hero>): MutableList<HeroHolder> {
        val holders = mutableListOf<HeroHolder>()
        heroes.forEach { hero ->
            val holder = HeroHolder(hero)
            holder.picked.set(selectedHeroes.find { it.name == hero.name } != null)
            holders.add(holder)
        }
        return holders
    }

    fun refresh() {
        typeHolders.forEach { it.selected.set(false) }
        selectedTypes.clear()
        selectedHeroes.clear()
        updateFilter()
    }


}