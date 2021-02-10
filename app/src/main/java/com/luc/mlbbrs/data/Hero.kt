package com.luc.mlbbrs.data

data class Hero(
    val name: String,
    val star: Int,
    var types: List<Int>,
    var item: Int
)

const val TANK = 1
const val PHYSICAL = 2
const val MAGICAL = 3