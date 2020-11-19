package com.bignerdranch.android.androidtestbaraholka.api

import com.bignerdranch.android.androidtestbaraholka.Model.CategoriesProduct
import com.bignerdranch.android.androidtestbaraholka.Model.Product

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header


interface SimpleApi {

    @GET("new_products")
    suspend fun getNewProduct():Response<List<Product>>

    @GET("now_products")
    suspend fun getNowProduct():Response<List<Product>>

    @GET("categories/77/products")
    suspend fun getCategoriesProduct():Response<CategoriesProduct>
}