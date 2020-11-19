package com.bignerdranch.android.androidtestbaraholka.Adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.androidtestbaraholka.DetailsActivity
import com.bignerdranch.android.androidtestbaraholka.Model.Categories
import com.bignerdranch.android.androidtestbaraholka.Model.CategoriesProduct
import com.bignerdranch.android.androidtestbaraholka.Model.Product
import com.bignerdranch.android.androidtestbaraholka.R
import com.bignerdranch.android.androidtestbaraholka.Utils.Constants
import com.squareup.picasso.Picasso

class CategoriesAdapter (private val context: FragmentActivity?, private val CategoriesProduct: List<Categories>, private val mRowLayout: Int)
    : RecyclerView.Adapter<CategoriesAdapter.ProductViewHolder>()

{

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.name.text = CategoriesProduct[position ].name



        holder.price.text = CategoriesProduct[position].price.toString().substring(0,3) + " " +
                CategoriesProduct[position].price.toString().substring(3)  +" тг."

        val picasso = Picasso.get()
        //  picasso.setIndicatorsEnabled(true)
        picasso
            .load(Constants.BASE_URL_IMG + CategoriesProduct[position].thumbnail?.substring(11))
            .fit()
            .into(holder.photo)
//        holder.photo.setImageResource(Products[position].Photo)



        holder.itemView.setOnClickListener{
            context!!.startActivity(
                Intent(context, DetailsActivity::class.java)
                .putExtra("Price", CategoriesProduct[position].price)
                .putExtra("Name",CategoriesProduct[position].name )
                .putExtra("Desc",CategoriesProduct[position].desc )
                .putExtra("Photo", CategoriesProduct[position].thumbnail?.substring(11))
                    .putExtra("Shop", CategoriesProduct[position].shop.name)
            )
        }
    }


    class ProductViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.categories_name)
        val price: TextView = itemView.findViewById(R.id.categories_price)
        val photo: ImageView = itemView.findViewById(R.id.categories_photo)
    }


    fun addAll(data: List<Categories>) {
        (CategoriesProduct as ArrayList<Categories>).addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(mRowLayout, parent,false)
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return CategoriesProduct.size
    }

}