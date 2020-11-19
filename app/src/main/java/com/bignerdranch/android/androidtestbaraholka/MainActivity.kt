package com.bignerdranch.android.androidtestbaraholka

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bignerdranch.android.androidtestbaraholka.Fragmets.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        if(intent.hasExtra("Key")){
//            val fragment = ShoppingCartFragment()
//            supportFragmentManager.beginTransaction().replace(R.id.container, fragment, fragment.javaClass.getSimpleName())
//                .commit()
//
//
//            Log.i("Key", "Key")
//        }
//



        bottomNavigationView.setOnNavigationItemSelectedListener (mOnNavigationItemSelectedListener)



        if (savedInstanceState == null && !intent.hasExtra("Key") ) {
            val fragment = MainkFragment()
            supportFragmentManager.beginTransaction().replace(R.id.container, fragment, fragment.javaClass.getSimpleName())
                .commit()
        }else{
            val fragment = ShoppingCartFragment()
            supportFragmentManager.beginTransaction().replace(R.id.container, fragment, fragment.javaClass.getSimpleName())
                .commit()
        }


    }
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
        when (menuItem.itemId) {
            R.id.navigation_main -> {
                val fragment = MainkFragment()
                supportFragmentManager.beginTransaction().replace(R.id.container, fragment, fragment.javaClass.getSimpleName())
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_categories -> {
                val fragment = CategoriesFragment()
                supportFragmentManager.beginTransaction().replace(R.id.container, fragment, fragment.javaClass.getSimpleName())
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
//            R.id.navigation_favorite -> {
//                val fragment = FavoriteFragment()
//                supportFragmentManager.beginTransaction().replace(R.id.container, fragment, fragment.javaClass.getSimpleName())
//                    .commit()
//                return@OnNavigationItemSelectedListener true
//            }
            R.id.navigation_basket -> {
                val fragment = ShoppingCartFragment()
                supportFragmentManager.beginTransaction().replace(R.id.container, fragment, fragment.javaClass.getSimpleName())
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_profile -> {
                val fragment = ProfileFragment()
                supportFragmentManager.beginTransaction().replace(R.id.container, fragment, fragment.javaClass.getSimpleName())
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}