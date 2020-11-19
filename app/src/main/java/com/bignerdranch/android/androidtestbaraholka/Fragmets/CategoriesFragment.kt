package com.bignerdranch.android.androidtestbaraholka.Fragmets

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdranch.android.androidtestbaraholka.Adapters.CategoriesAdapter
import com.bignerdranch.android.androidtestbaraholka.Adapters.ProductsAdapter
import com.bignerdranch.android.androidtestbaraholka.MainViewModel
import com.bignerdranch.android.androidtestbaraholka.MainViewModelFactory
import com.bignerdranch.android.androidtestbaraholka.Model.Categories
import com.bignerdranch.android.androidtestbaraholka.Model.Product
import com.bignerdranch.android.androidtestbaraholka.R
import com.bignerdranch.android.androidtestbaraholka.repository.Repository
import kotlinx.android.synthetic.main.fragment_categories.*
import kotlinx.android.synthetic.main.fragment_main.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var viewModel: MainViewModel

var CategoriesAdapter: CategoriesAdapter? = null

class CategoriesFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CategoriesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        val categoriesList: ArrayList<Categories> = arrayListOf()
        val layoutmanager = LinearLayoutManager(context)

        categories_recycler_view.layoutManager = layoutmanager
        CategoriesAdapter = CategoriesAdapter(activity, mutableListOf(), R.layout.categories_item)

        categories_recycler_view.adapter = CategoriesAdapter

        getCategoriesProduct()

        viewModel.getCategoriesProduct()
        viewModel.getCategoriesProductResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {

                for (datum in response.body()!!.products.data!!) {
                    categoriesList.add(datum)
                    Log.i("Iphone", datum.toString())
                }

                CategoriesAdapter!!.addAll(categoriesList)


//                response.body()!!.forEach {
//                    newProductList?.add(it)
//                }
//                ProductsAdapter!!.addAll(newProductList as List<Product>)
            }
        })











    }


    private fun getCategoriesProduct(){

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }

}