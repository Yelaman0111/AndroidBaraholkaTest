package com.bignerdranch.android.androidtestbaraholka.Model

data class CategoriesProduct (
    val products: Products
)

class Products {
    val data: List<Categories>? = null
}

data class Categories (
    val name: String,
    val desc: String,
    val price: Int,
    val thumbnail: String,
    val shop: Shop
)

class Shop (
    val name: String
)
