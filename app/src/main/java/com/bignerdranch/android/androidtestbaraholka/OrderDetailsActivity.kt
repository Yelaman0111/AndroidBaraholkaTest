package com.bignerdranch.android.androidtestbaraholka

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bignerdranch.android.androidtestbaraholka.DB.DatabaseHandler
import com.bignerdranch.android.androidtestbaraholka.Utils.Constants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_order_details.*

class OrderDetailsActivity : AppCompatActivity() {
    var dbHandler: DatabaseHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_details)


        val Name = intent.getStringExtra("Name")

        dbHandler = DatabaseHandler(this)

        var orderedProduct = dbHandler!!.getAllOrder()


//        Log.i("ORDERDETAIL", orderedProduct.toString())


        val item = orderedProduct.findLast { it.customerName == Name }
//        Log.i("ChatID", "updateChat $id    $item")
        order_address.text = item?.customerCity

        val picasso = Picasso.get()
        //  picasso.setIndicatorsEnabled(true)
        picasso
            .load(item?.thumbnail)
            .fit()
            .into(order_photo_buy)

        order_name_buy.text = item?.name
//        order_price_buy.text = item?.price
//        order_final_price.text = item?.totalProductPrice
        order_product_price.text = item?.totalPrice
        order_total_price.text = item?.totalProductPrice
        order_delivery_price.text = item?.deliveryPrice
        editTextTextPersonName.text = item?.customerName
        editTextTextPersonSecondName.text = item?.customerSecName
        editTextTextPersonPhone.text = item?.customerPhone



        Log.i("ORDERDETAIL", item.toString())











    }
}