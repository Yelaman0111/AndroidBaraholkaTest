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
import kotlinx.android.synthetic.main.product_item.view.*

class ProductsAdapter(private val context: FragmentActivity?, private val Products: List<Product>, private val mRowLayout: Int)
    :RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>()

{

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.name.text = Products[position].Name
        holder.price.text = Products[position].Price
        holder.photo.setImageResource(Products[position].Photo)



        holder.itemView.setOnClickListener{
            context!!.startActivity(Intent(context, DetailsActivity::class.java)
                .putExtra("Price", Products[position].Price)
                .putExtra("Name",Products[position].Name )
                .putExtra("Desc",Products[position].Description )
                .putExtra("Photo", Products[position].Photo)
            )
        }
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