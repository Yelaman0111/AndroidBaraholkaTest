package com.bignerdranch.android.androidtestbaraholka.Fragmets

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdranch.android.androidtestbaraholka.Adapters.ProductsAdapter
import com.bignerdranch.android.androidtestbaraholka.MainViewModel
import com.bignerdranch.android.androidtestbaraholka.MainViewModelFactory
import com.bignerdranch.android.androidtestbaraholka.Model.Product
import com.bignerdranch.android.androidtestbaraholka.R
import com.bignerdranch.android.androidtestbaraholka.repository.Repository
import kotlinx.android.synthetic.main.fragment_main.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

    var ProductsAdapter:ProductsAdapter? = null
    var ProductsAdapter2:ProductsAdapter? = null

    private lateinit var viewModel: MainViewModel


class MainkFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_main, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val newProductList: ArrayList<Product> = arrayListOf()
        val nowProductList: ArrayList<Product> = arrayListOf()

        val layoutmanager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val layoutmanager2 = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        new_products_recycler_view.layoutManager = layoutmanager
        now_products_recycler_view.layoutManager = layoutmanager2

        ProductsAdapter = ProductsAdapter(activity, mutableListOf(), R.layout.product_item)
        ProductsAdapter2 = ProductsAdapter(activity, mutableListOf(), R.layout.product_item)

        new_products_recycler_view.adapter = ProductsAdapter
        now_products_recycler_view.adapter = ProductsAdapter2

        getNewProduct()
        getNowProduct()

        viewModel.getNewProduct()
        viewModel.getNewProductResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {
                response.body()!!.forEach {
                    newProductList?.add(it)
                }
                ProductsAdapter!!.addAll(newProductList as List<Product>)
            }
        })

        viewModel.getNowProduct()
        viewModel.getNowProductResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {
                response.body()!!.forEach {
                    nowProductList?.add(it)
                }
                ProductsAdapter2!!.addAll(nowProductList as List<Product>)
            }
        })




    }

    private fun getNewProduct(){

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }

    private fun getNowProduct(){

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }


}
