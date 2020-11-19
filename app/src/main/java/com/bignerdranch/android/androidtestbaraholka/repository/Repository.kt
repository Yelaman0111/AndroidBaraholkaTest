package com.bignerdranch.android.androidtestbaraholka.repository

import com.bignerdranch.android.androidtestbaraholka.Model.CategoriesProduct
import com.bignerdranch.android.androidtestbaraholka.Model.Product

import com.bignerdranch.android.androidtestbaraholka.api.RetrofitInstance
import retrofit2.Response

class Repository {

        suspend fun getNewProduct(): Response<List<Product>> {
            return RetrofitInstance.api.getNewProduct()
        }

        suspend fun getNowProduct(): Response<List<Product>> {
            return RetrofitInstance.api.getNowProduct()
        }

        suspend fun getCategoriesProduct(): Response<CategoriesProduct> {
            return RetrofitInstance.api.getCategoriesProduct()
        }
}