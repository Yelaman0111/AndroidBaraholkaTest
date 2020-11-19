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
import com.bignerdranch.android.androidtestbaraholka.Model.Product
import com.bignerdranch.android.androidtestbaraholka.R
import com.bignerdranch.android.androidtestbaraholka.Utils.Constants.Companion.BASE_URL_IMG
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.product_item.view.*

class ProductsAdapter(private val context: FragmentActivity?, private val Products: List<Product>, private val mRowLayout: Int)
    :RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>()

{

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.name.text = Products[position].name
        holder.price.text = Products[position].price + " тг."

        val picasso = Picasso.get()
      //  picasso.setIndicatorsEnabled(true)
        picasso
            .load(BASE_URL_IMG + Products[position].thumbnail.substring(11))
            .fit()
            .into(holder.photo)
//        holder.photo.setImageResource(Products[position].Photo)


//
//        holder.itemView.setOnClickListener{
//            context!!.startActivity(Intent(context, DetailsActivity::class.java)
//                .putExtra("Price", Products[position].price)
//                .putExtra("Name",Products[position].name )
//                .putExtra("Desc",Products[position].desc )
////                .putExtra("Photo", Products[position].Photo)
//            )
//        }
    }


    class ProductViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.product_name)
        val price: TextView = itemView.findViewById(R.id.product_price)
        val photo: ImageView = itemView.findViewById(R.id.product_photo)
    }


    fun addAll(data: List<Product>) {
        (Products as ArrayList<Product>).addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(mRowLayout, parent,false)
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return Products.size
    }
}