package com.bignerdranch.android.androidtestbaraholka

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_buy_product.*

class BuyProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy_product)



        val Name =intent.getStringExtra("Name")
        val Price =intent.getStringExtra("Price")

        val Photo = intent.getIntExtra("Photo", 1)
        val Count = intent.getIntExtra("Count", 1)



        product_name_buy.text = Name
        product_price_buy.text = Price
        product_photo_buy.setImageResource(Photo)
        product_count.text = " * " + Count.toString()
        product_final_price.text = " = " + Count * Price.substring(0, Price.length - 3).replace(" ", "").toInt() + "тг"





    }
}