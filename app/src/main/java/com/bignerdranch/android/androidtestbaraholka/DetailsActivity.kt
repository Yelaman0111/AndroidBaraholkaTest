package com.bignerdranch.android.androidtestbaraholka

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bignerdranch.android.androidtestbaraholka.Fragmets.ShoppingCartFragment
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)


        val Name =intent.getStringExtra("Name")
        val Price =intent.getStringExtra("Price")
        val Desc =intent.getStringExtra("Desc")
        val Photo = intent.getIntExtra("Photo", 1)


        photo_detail.setImageResource(Photo)
        name_detail.text = Name
        price_detail.text = Price
        desc_detail.text = Desc

        add_to_cart.setOnClickListener {
            Toast.makeText(this, "Продукт добавлен в корзину", Toast.LENGTH_SHORT).show()

            //Запрос на сервер на добавление товара в корзину


        }


        back_layout.setOnClickListener {
            onBackPressed()
        }

        buy_product.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("Key", "1")
            startActivity(intent)
           //запрос на сервер
        }





    }
}