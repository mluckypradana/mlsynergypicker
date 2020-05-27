package com.luc.mobilelegends.ui.vm

import android.app.Application
import com.luc.mobilelegends.R
import com.luc.mobilelegends.core.BaseListAdapter
import com.luc.mobilelegends.core.BaseViewModel
import com.luc.mobilelegends.data.Hero
import com.luc.mobilelegends.data.Type
import com.luc.mobilelegends.repo.HeroRepo
import com.luc.mobilelegends.repo.TypeRepo
import com.luc.mobilelegends.ui.holder.HeroHolder
import com.luc.mobilelegends.ui.holder.TypeHolder
import org.koin.core.inject

class MainVm(context: Application) : BaseViewModel(context) {
    private val types: List<Type>
    private val heroes: MutableList<Hero> = mutableListOf()
    private val recommendedHeroes: MutableList<Hero> = mutableListOf()
    private val selectedTypes: MutableList<Type> = mutableListOf()
    private val typeRepo: TypeRepo by inject()
    private val heroRepo: HeroRepo by inject()
    val typeAdapter: BaseListAdapter = BaseListAdapter(R.layout.item_type)
    val heroAdapter: BaseListAdapter = BaseListAdapter(R.layout.item_hero)
    val recommendedAdapter: BaseListAdapter = BaseListAdapter(R.layout.item_hero)

    init {
        types = typeRepo.getTypes()
        initTypeAdapter()
        initHeroAdapter()
    }

    private fun initTypeAdapter() {
        val holders = mutableListOf<TypeHolder>()
        types.forEach { holders.add(TypeHolder(it)) }
        typeAdapter.items = holders
        typeAdapter.onItemClick = { _, position ->
            val selected = holders[position].selected.get() ?: false
            if (!selected)
                selectedTypes.add(holders[position].data)
            else
                selectedTypes.remove(holders[position].data)
            updateFilter()
            holders[position].selected.set(!selected)
        }
    }

    private fun initHeroAdapter() {
        heroAdapter.items = getHeroHolders(heroes)
        recommendedAdapter.items = getHeroHolders(recommendedHeroes)
    }

    private fun updateFilter() {
        recommendedHeroes.clear()
        if (selectedTypes.isNotEmpty())
            recommendedHeroes.addAll(heroRepo.getRecommendedMatchesByType(selectedTypes))
        recommendedAdapter.items = getHeroHolders(recommendedHeroes)
        recommendedAdapter.notifyDataSetChanged()

        heroes.clear()
        if (selectedTypes.isNotEmpty())
            heroes.addAll(heroRepo.getMatchesByType(selectedTypes))
        heroes.removeAll(recommendedHeroes)
        heroAdapter.items = getHeroHolders(heroes)
        heroAdapter.notifyDataSetChanged()

    }

    fun getHeroHolders(heroes: MutableList<Hero>): MutableList<HeroHolder> {
        val holders = mutableListOf<HeroHolder>()
        heroes.forEach { holders.add(HeroHolder(it)) }
        return holders
    }


}