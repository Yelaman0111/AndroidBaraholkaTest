package com.bignerdranch.android.androidtestbaraholka.Adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.androidtestbaraholka.BuyProductActivity
import com.bignerdranch.android.androidtestbaraholka.DetailsActivity
import com.bignerdranch.android.androidtestbaraholka.Fragmets.SignUpFragment
import com.bignerdranch.android.androidtestbaraholka.Model.Product
import com.bignerdranch.android.androidtestbaraholka.R
import org.w3c.dom.Text

class ShoppingCartAdapter (private val context: FragmentActivity?, private val Products: List<Product>, private val mRowLayout: Int)
    : RecyclerView.Adapter<ShoppingCartAdapter.ProductViewHolder>()

{

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.name.text = Products[position].Name
        holder.price.text = Products[position].Price
        holder.photo.setImageResource(Products[position].Photo)

        var count = 1

        holder.buttonAdd.setOnClickListener {
            count++
            holder.countText.text =  count.toString()

            if (count > 1) holder.buttonDelete.setBackgroundResource(R.drawable.ic_baseline_remove_24)


        }




        holder.buttonDelete.setOnClickListener {
            count--
            if(count == 1) holder.buttonDelete.setBackgroundResource(R.drawable.ic_baseline_restore_from_trash_24)
            if(count < 1) delete(position)


            holder.countText.text = count.toString()
        }



        holder.buttonBuy.setOnClickListener{
            context!!.startActivity(Intent(context, BuyProductActivity::class.java)
                .putExtra("Price", Products[position].Price)
                .putExtra("Name",Products[position].Name )
                .putExtra("Desc",Products[position].Description )
                .putExtra("Photo", Products[position].Photo)
                .putExtra("Count", count)
            )
        }
    }


    class ProductViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.cart_product_name)
        val price: TextView = itemView.findViewById(R.id.cart_product_price)
        val photo: ImageView = itemView.findViewById(R.id.cart_product_photo)

        val countText: TextView = itemView.findViewById(R.id.cart_count)
        val buttonBuy : Button = itemView.findViewById(R.id.cart_buy)
        val buttonDelete: ImageButton = itemView.findViewById(R.id.cart_delete_from)
        val buttonAdd: ImageButton = itemView.findViewById(R.id.cart_add)
    }


    fun addAll(data: List<Product>) {
        (Products as ArrayList<Product>).addAll(data)
        notifyDataSetChanged()
    }

    fun delete(position: Int) {
        (Products as ArrayList<Product>).removeAt(position)
//        (Products as ArrayList<Product>).addAll(data)
        notifyItemRemoved(position)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(mRowLayout, parent,false)
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return Products.size
    }
}