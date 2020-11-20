package com.bignerdranch.android.androidtestbaraholka.Fragmets

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdranch.android.androidtestbaraholka.Adapters.ProductsAdapter
import com.bignerdranch.android.androidtestbaraholka.Adapters.ShoppingCartAdapter
import com.bignerdranch.android.androidtestbaraholka.DB.DatabaseHandler
import com.bignerdranch.android.androidtestbaraholka.Model.Product
import com.bignerdranch.android.androidtestbaraholka.R
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_shopping_cart.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BasketFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

var ShoppingCartAdapter: ShoppingCartAdapter? = null

class ShoppingCartFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shopping_cart, container, false)
    }

    var dbHandler: DatabaseHandler? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        dbHandler = DatabaseHandler(context as Context)

        var productList = dbHandler!!.getAllProduct()

//        productList.forEach {
//            Log.i("Product", it.toString())
//        }

        val layoutmanager = LinearLayoutManager(context)

        shopping_cart_recycler.layoutManager = layoutmanager

        ShoppingCartAdapter = com.bignerdranch.android.androidtestbaraholka.Adapters.ShoppingCartAdapter(
            activity,
            mutableListOf(),
            R.layout.shopping_cart_item
        )
        shopping_cart_recycler.adapter = ShoppingCartAdapter

        ShoppingCartAdapter!!.addAll(productList)



    }
}
