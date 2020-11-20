package com.bignerdranch.android.androidtestbaraholka

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bignerdranch.android.androidtestbaraholka.DB.DatabaseHandler
import com.bignerdranch.android.androidtestbaraholka.Model.Product
import com.bignerdranch.android.androidtestbaraholka.Utils.Constants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {
//    var db: DB? = null

    public var dbHandler: DatabaseHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)


        val Name =intent.getStringExtra("Name")
        val Price =intent.getIntExtra("Price", 1)
        val Desc =intent.getStringExtra("Desc")
        val Photo = intent.getStringExtra("Photo")
        val Shop = intent.getStringExtra("Shop")
        val ID: Int = intent.getIntExtra("ID", 1)

//        Log.i("DETAILS", "Name: $Name   Price: $Price   Desc: $Desc   Photo: $Photo")

        var product: Product = Product(ID, Name,Photo,  Price.toString(), Desc)

//        Log.i("DETAILS", product.toString())

        val picasso = Picasso.get()
        //  picasso.setIndicatorsEnabled(true)
        picasso
            .load(Constants.BASE_URL_IMG + Photo)
            .fit()
            .into(photo_detail)


        Log.i("DETAILS",Constants.BASE_URL_IMG + Photo)

//        photo_detail.setImageResource(Photo)
        name_detail.text = Name
        price_detail.text = Price.toString().substring(0,3) + " " +
                Price.toString().substring(3)  +" тг."

        desc_detail.text = Desc



        shop_name.text = Shop




        back_layout.setOnClickListener {
            onBackPressed()
        }

        buy_product.setOnClickListener {
//            val intent = Intent(this, MainActivity::class.java)
//            intent.putExtra("Key", "1")
//            startActivity(intent)
           //запрос на сервер


            var productList = dbHandler!!.getAllProduct()

            productList.forEach{
                Log.i("Product", it.toString())
            }
        }

        dbHandler = DatabaseHandler(this)

        add_to_cart.setOnClickListener {
            var success: Boolean = false
            success = dbHandler!!.addProduct(product)

            if(success) Toast.makeText(this, "Добавлено в корзину", Toast.LENGTH_SHORT).show()
        }



    }
}