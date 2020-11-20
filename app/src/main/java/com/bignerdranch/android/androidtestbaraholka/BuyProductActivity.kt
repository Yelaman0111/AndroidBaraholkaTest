package com.bignerdranch.android.androidtestbaraholka

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import com.bignerdranch.android.androidtestbaraholka.DB.DatabaseHandler
import com.bignerdranch.android.androidtestbaraholka.Model.OrderedProduct
import com.bignerdranch.android.androidtestbaraholka.Utils.Constants
import com.bignerdranch.android.androidtestbaraholka.Utils.Constants.Companion.BASE_URL_IMG
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_buy_product.*

class BuyProductActivity : AppCompatActivity() {

    var dbHandler: DatabaseHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy_product)



        val Name =intent.getStringExtra("Name")
        val Price =intent.getStringExtra("Price")
        val OrderID = intent.getIntExtra("ID", 1)
        val Photo = intent.getStringExtra("Photo")
        val Count = intent.getIntExtra("Count", 1)

        var deleveryPrice: Int = 0

        var city: String? = null


        product_name_buy.text = Name
        product_price_buy.text = Price
        val picasso = Picasso.get()

        picasso
            .load(BASE_URL_IMG + Photo)
            .fit()
            .into(product_photo_buy)

        Log.i("PHOTO", BASE_URL_IMG + Photo)


       // product_photo_buy.setImageResource(Photo)
        product_count.text = " * " + Count.toString()
        var total = Count * Price.toInt()
        product_final_price.text = " = " + total.toString().substring(0,3) + " " + total.toString().substring(3)     + " тг."

        product_in_del_price.text = total.toString().substring(0,3) + " " + total.toString().substring(3) + " тг."



        dbHandler = DatabaseHandler(this)




        buy_button.setOnClickListener {
            dbHandler!!.addOrder(OrderedProduct(Name, BASE_URL_IMG + Photo,
                Price, editTextTextPersonName.text.toString(), editTextTextPersonSecondName.text.toString(),
                city, editTextTextPersonPhone.text.toString(), product_final_price.text.toString(),
                delivery_price.text.toString(), final_price.text.toString()
                ))
            Log.i("CITY", OrderedProduct(Name, BASE_URL_IMG + Photo,
                Price, editTextTextPersonName.text.toString(), editTextTextPersonSecondName.text.toString(),
                city, editTextTextPersonPhone.text.toString(), product_final_price.text.toString(),
                delivery_price.text.toString(), final_price.text.toString()
            ).toString())

            Toast.makeText(this, "Ваш заказ оформлен", Toast.LENGTH_SHORT).show()



        }


        var cityArray = arrayListOf<String>( "Алматы","Нур-Султан","ВКО", "СКО", "ЗКО","Шимкент","Атырау","Карганда")








        val adapter: ArrayAdapter<String> = object: ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            cityArray
        ){
            override fun getDropDownView(
                position: Int,
                convertView: View?,
                parent: ViewGroup
            ): View {
                val view: TextView = super.getDropDownView(
                    position,
                    convertView,
                    parent
                ) as TextView
                // set item text bold
                view.setTypeface(view.typeface, Typeface.BOLD)

                // set selected item style
                if (position == spinner.selectedItemPosition){
                    view.background = ColorDrawable(Color.parseColor("#FAEBD7"))
                    view.setTextColor(Color.parseColor("#008000"))
                }

                return view
            }
        }

        spinner.adapter = adapter


        spinner.onItemSelectedListener = object : AdapterView.OnItemClickListener,
            AdapterView.OnItemSelectedListener {

            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                TODO("Not yet implemented")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if(id.toInt() == 0){
                    deleveryPrice = 200
                }else{
                    deleveryPrice = 700
                }
                //city = city_array.get
                delivery_price.text = deleveryPrice.toString() + " тг."
                final_price.text = (total + deleveryPrice).toString().substring(0,3) + " " + (total + deleveryPrice).toString().substring(3) + " тг."
//                Toast.makeText(this@BuyProductActivity, "$view", Toast.LENGTH_LONG).show()

                city =  parent?.getItemAtPosition(position).toString()
            }
        }

    }
}