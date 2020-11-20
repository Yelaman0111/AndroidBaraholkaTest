package com.bignerdranch.android.androidtestbaraholka.Fragmets

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdranch.android.androidtestbaraholka.Adapters.CategoriesAdapter
import com.bignerdranch.android.androidtestbaraholka.Adapters.OrderedAdapter
import com.bignerdranch.android.androidtestbaraholka.DB.DatabaseHandler
import com.bignerdranch.android.androidtestbaraholka.MainActivity
import com.bignerdranch.android.androidtestbaraholka.Model.Categories
import com.bignerdranch.android.androidtestbaraholka.Model.OrderedProduct
import com.bignerdranch.android.androidtestbaraholka.R
import kotlinx.android.synthetic.main.fragment_categories.*
import kotlinx.android.synthetic.main.fragment_profile.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var OrderedAdapter: OrderedAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }








    }

    var dbHandler: DatabaseHandler? = null
    override fun onActivityCreated(savedInstanceState: Bundle?) {


        val layoutmanager = LinearLayoutManager(context)
//        val categoriesList: ArrayList<OrderedProduct> = arrayListOf()

        ordered_recyclerview.layoutManager = layoutmanager

        OrderedAdapter =
            com.bignerdranch.android.androidtestbaraholka.Adapters.OrderedAdapter(
                activity,
                mutableListOf(),
                R.layout.ordered_product_item
            )

        ordered_recyclerview.adapter = OrderedAdapter

//        categoriesList.add(OrderedProduct(1,"Чайник", "12345"))

        dbHandler = DatabaseHandler(context as Context)

        var productList = dbHandler!!.getAllOrder()


//        categoriesList.addAll(productList)

        OrderedAdapter!!.addAll(productList)
















//        signUp.setOnClickListener {
//
//            requireActivity().supportFragmentManager.beginTransaction()
//                .replace(R.id.container, SignUpFragment())
//
//                .commit()
//
//        }
//
//        button_login.setOnClickListener {
//            //запрос на сервер вход пользователя
//        }





        super.onActivityCreated(savedInstanceState)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

}