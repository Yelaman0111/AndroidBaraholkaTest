package com.bignerdranch.android.androidtestbaraholka

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bignerdranch.android.androidtestbaraholka.Model.CategoriesProduct
import com.bignerdranch.android.androidtestbaraholka.Model.Product
import com.bignerdranch.android.androidtestbaraholka.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response


class MainViewModel(private val repository: Repository): ViewModel() {
    val getNewProductResponse: MutableLiveData<Response<List<Product>>> = MutableLiveData()
    val getNowProductResponse: MutableLiveData<Response<List<Product>>> = MutableLiveData()
    val getCategoriesProductResponse: MutableLiveData<Response<CategoriesProduct>> = MutableLiveData()




    fun getNewProduct(){
        viewModelScope.launch {
            val response: Response<List<Product>> = repository.getNewProduct()
            getNewProductResponse.value = response
        }
    }

    fun getNowProduct(){
        viewModelScope.launch {
            val response: Response<List<Product>> = repository.getNowProduct()
            getNowProductResponse.value = response
        }
    }


    fun getCategoriesProduct(){
        viewModelScope.launch {
            val response: Response<CategoriesProduct> = repository.getCategoriesProduct()
            getCategoriesProductResponse.value = response
        }
    }
}
