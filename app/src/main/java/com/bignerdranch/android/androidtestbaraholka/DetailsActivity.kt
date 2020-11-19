package com.bignerdranch.android.androidtestbaraholka

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bignerdranch.android.androidtestbaraholka.Fragmets.ShoppingCartFragment
import com.bignerdranch.android.androidtestbaraholka.Utils.Constants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)


        val Name =intent.getStringExtra("Name")
        val Price =intent.getIntExtra("Price", 1)
        val Desc =intent.getStringExtra("Desc")
        val Photo = intent.getStringExtra("Photo")
        val Shop = intent.getStringExtra("Shop")
        Log.i("DETAILS", "Name: $Name   Price: $Price   Desc: $Desc   Photo: $Photo")

        val picasso = Picasso.get()
        //  picasso.setIndicatorsEnabled(true)
        picasso
            .load(Constants.BASE_URL_IMG + Photo)
            .fit()
            .into(photo_detail)

//        photo_detail.setImageResource(Photo)
        name_detail.text = Name
        price_detail.text = Price.toString().substring(0,3) + " " +
                Price.toString().substring(3)  +" тг."

        desc_detail.text = Desc

        add_to_cart.setOnClickListener {
            Toast.makeText(this, "Продукт добавлен в корзину", Toast.LENGTH_SHORT).show()

            //Запрос на сервер на добавление товара в корзину


        }

        shop_name.text = Shop




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