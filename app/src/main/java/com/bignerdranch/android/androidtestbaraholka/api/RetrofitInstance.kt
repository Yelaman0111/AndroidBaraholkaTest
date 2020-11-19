package com.bignerdranch.android.androidtestbaraholka.api

import com.bignerdranch.android.androidtestbaraholka.Utils.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val client = OkHttpClient.Builder().apply {
        addInterceptor(MyInterceptor())
    }.build()

    private  val retrofit: Retrofit by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: SimpleApi by lazy{
        retrofit.create(SimpleApi::class.java)
    }

}