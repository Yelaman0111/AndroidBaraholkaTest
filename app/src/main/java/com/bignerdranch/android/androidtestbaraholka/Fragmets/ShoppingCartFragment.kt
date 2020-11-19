package com.bignerdranch.android.androidtestbaraholka.Fragmets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdranch.android.androidtestbaraholka.Adapters.ProductsAdapter
import com.bignerdranch.android.androidtestbaraholka.Adapters.ShoppingCartAdapter
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)



        val layoutmanager = LinearLayoutManager(context)

        shopping_cart_recycler.layoutManager = layoutmanager

        ShoppingCartAdapter = com.bignerdranch.android.androidtestbaraholka.Adapters.ShoppingCartAdapter(
            activity,
            mutableListOf(),
            R.layout.shopping_cart_item
        )
        shopping_cart_recycler.adapter = ShoppingCartAdapter



            //запрос на сервер на получение списка товаров из корзины
//        getProductsInCartFormServer()

    }

//    private fun getProductsInCartFormServer() {
//        val Product1: Product = Product("Apple iPhone 11 pro Max 64gb Midnight Green",R.drawable.iphone7,"499 999 тг",
//            "Дисплей\n" +
//                    "Все новые смартфоны Apple образца 2020 года получили OLED-экраны Super Retina XDR. У iPhone 12 он 6,1-дюймовый.\n" +
//                    "\n" +
//                    "Процессор и память\n" +
//                    "Аппаратной основой iPhone 12 стал флагманский 5-нм чипсет Apple A14 Bionic. " +
//                    "Он обеспечивает феноменальную производительность. Ждем тестов. " +
//                    "Но, судя по тому, какие результаты эта SoC демонстрировала внутри iPad Air 4, переживать по этому поводу не стоит." +
//                    "\n" +
//                    "Камеры\n" +
//                    "Основная камера у iPhone 12 двойная — используется широкоугольный и сверхширокоугольный объективы. " +
//                    "Оба 12-мегапиксельные. Присутствует оптическая стабилизация. Угол обзора — 120 градусов." +
//                    "\n" +
//                    "Аккумулятор\n" +
//                    "Емкость аккумулятора Apple традиционно не раскрыла. Но, по словам инсайдерам, в iPhone 12 стоит батарея на 2775 мА*ч. Это даже меньше, чем у iPhone 11.\n" +
//                    "\n" +
//                    "Время автономной работы – до 17 часов воспроизведения видео, 11 часов потоковой передачи, 65 часов звука.\n" +
//                    "\n" +
//                    "iPhone 12 поддерживает новую систему магнитной беспроводной зарядки Magsafe. Ее мощность составляет 15 Вт.")
//        val Product2: Product = Product("Apple iPhone 10 pro Max 128gb Gray",R.drawable.iphone2,"599 999 тг","Дисплей\n" +
//                "Все новые смартфоны Apple образца 2020 года получили OLED-экраны Super Retina XDR. У iPhone 12 он 6,1-дюймовый.\n" +
//                "\n" +
//                "Процессор и память\n" +
//                "Аппаратной основой iPhone 12 стал флагманский 5-нм чипсет Apple A14 Bionic. " +
//                "Он обеспечивает феноменальную производительность. Ждем тестов. " +
//                "Но, судя по тому, какие результаты эта SoC демонстрировала внутри iPad Air 4, переживать по этому поводу не стоит." +
//                "\n" +
//                "Камеры\n" +
//                "Основная камера у iPhone 12 двойная — используется широкоугольный и сверхширокоугольный объективы. " +
//                "Оба 12-мегапиксельные. Присутствует оптическая стабилизация. Угол обзора — 120 градусов." +
//                "\n" +
//                "Аккумулятор\n" +
//                "Емкость аккумулятора Apple традиционно не раскрыла. Но, по словам инсайдерам, в iPhone 12 стоит батарея на 2775 мА*ч. Это даже меньше, чем у iPhone 11.\n" +
//                "\n" +
//                "Время автономной работы – до 17 часов воспроизведения видео, 11 часов потоковой передачи, 65 часов звука.\n" +
//                "\n" +
//                "iPhone 12 поддерживает новую систему магнитной беспроводной зарядки Magsafe. Ее мощность составляет 15 Вт.")
//        val Product3: Product = Product("Apple iPhone 12 pro Max 264gb Midnight Black",R.drawable.iphone7,"300 000 тг","Дисплей\n" +
//                "Все новые смартфоны Apple образца 2020 года получили OLED-экраны Super Retina XDR. У iPhone 12 он 6,1-дюймовый.\n" +
//                "\n" +
//                "Процессор и память\n" +
//                "Аппаратной основой iPhone 12 стал флагманский 5-нм чипсет Apple A14 Bionic. " +
//                "Он обеспечивает феноменальную производительность. Ждем тестов. " +
//                "Но, судя по тому, какие результаты эта SoC демонстрировала внутри iPad Air 4, переживать по этому поводу не стоит." +
//                "\n" +
//                "Камеры\n" +
//                "Основная камера у iPhone 12 двойная — используется широкоугольный и сверхширокоугольный объективы. " +
//                "Оба 12-мегапиксельные. Присутствует оптическая стабилизация. Угол обзора — 120 градусов." +
//                "\n" +
//                "Аккумулятор\n" +
//                "Емкость аккумулятора Apple традиционно не раскрыла. Но, по словам инсайдерам, в iPhone 12 стоит батарея на 2775 мА*ч. Это даже меньше, чем у iPhone 11.\n" +
//                "\n" +
//                "Время автономной работы – до 17 часов воспроизведения видео, 11 часов потоковой передачи, 65 часов звука.\n" +
//                "\n" +
//                "iPhone 12 поддерживает новую систему магнитной беспроводной зарядки Magsafe. Ее мощность составляет 15 Вт.")
//
//        val ProductList: List<Product>? = arrayListOf()
//        (ProductList as ArrayList).add(Product1)
//        ProductList?.add(Product2)
//        ProductList?.add(Product3)
//
//
//    ShoppingCartAdapter?.addAll(ProductList)
//
//
//
//
//
//
//    }
}