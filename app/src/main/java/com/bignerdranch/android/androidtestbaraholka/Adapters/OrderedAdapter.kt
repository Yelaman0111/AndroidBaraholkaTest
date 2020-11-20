package com.bignerdranch.android.androidtestbaraholka.Adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.androidtestbaraholka.DetailsActivity
import com.bignerdranch.android.androidtestbaraholka.Model.OrderedProduct
import com.bignerdranch.android.androidtestbaraholka.Model.Product
import com.bignerdranch.android.androidtestbaraholka.OrderDetailsActivity
import com.bignerdranch.android.androidtestbaraholka.R
import com.bignerdranch.android.androidtestbaraholka.Utils.Constants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.ordered_product_item.view.*
import org.w3c.dom.Text

class OrderedAdapter (private val context: FragmentActivity?, private val Products: List<OrderedProduct>, private val mRowLayout: Int)
    : RecyclerView.Adapter<OrderedAdapter.ProductViewHolder>()

{

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

//        holder.orderNum.append( Products[position].id.toString())
        holder.price.text = Products[position].totalProductPrice + " тг."
        holder.product.text = Products[position].name

        holder.open_details_btn.setOnClickListener {
            context!!.startActivity(Intent(context, OrderDetailsActivity::class.java)
                .putExtra("Name", Products[position].customerName)
            )
        }













//        val picasso = Picasso.get()
//        //  picasso.setIndicatorsEnabled(true)
//        picasso
//            .load(Constants.BASE_URL_IMG + Products[position].thumbnail.substring(11))
//            .fit()
//            .into(holder.photo)
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
//        val name: TextView = itemView.findViewById(R.id.product_name)
//        val price: TextView = itemView.findViewById(R.id.product_price)
//        val photo: ImageView = itemView.findViewById(R.id.product_photo)

        val orderNum: TextView = itemView.order_num
        val price: TextView = itemView.total_price_cart
        val product: TextView = itemView.products
        val open_details_btn: Button = itemView.open_details_btn
    }


    fun addAll(data: List<OrderedProduct>) {
        (Products as ArrayList<OrderedProduct>).addAll(data)
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