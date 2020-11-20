package com.bignerdranch.android.androidtestbaraholka.DB
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.bignerdranch.android.androidtestbaraholka.Model.OrderedProduct
import com.bignerdranch.android.androidtestbaraholka.Model.Product
import kotlinx.coroutines.processNextEventInCurrentThread

class DatabaseHandler(context: Context):
            SQLiteOpenHelper(context,DB_NAME, null, DB_VERSION){

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE = "CREATE TABLE $TABLE_NAME " +
                "($ID Integer PRIMARY KEY, $PRODUCT_NAME TEXT, $PRODUCT_PRICE TEXT," +
                " $PRODUCT_DESC TEXT, $PRODUCT_PHOTO TEXT, $PRODUCT_ID TEXT)"


        val CREATE_TABLE_ORDER = "CREATE TABLE $ORDER_TABLE " +
                "($ID Integer PRIMARY KEY, $PRODUCT_NAME TEXT, $PRODUCT_PHOTO TEXT," +
                " $PRODUCT_PRICE TEXT, $CUSTOMER_NAME TEXT, $CUSTOMER_SEC_NAME TEXT, $CUSTOMER_CITY TEXT, $CUSTOMER_PHONE TEXT," +
                " $CUSTOMER_TOTAL_PRICE TEXT, $CUSTOMER_DLIVERY_PRICE TEXT, $CUSTOMER_TOTAL_PRODUCT_PRICE)"
        db?.execSQL(CREATE_TABLE_ORDER)
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }


    fun deleteProduct(id: Int){
        val db = this.writableDatabase
        val deleteQuery = "DELETE FROM $TABLE_NAME WHERE $PRODUCT_ID = $id"
        db.execSQL(deleteQuery)
        db.close()
    }

    fun getOrder(name: String): OrderedProduct?{
        val db = this.readableDatabase
        val getQuery = "SELECT * FROM $ORDER_TABLE WHERE $CUSTOMER_NAME = $name"
//        val selectAllQuery = "SELECT * FROM $TABLE_NAME"

        val cursor = db.rawQuery(getQuery, null)
        var product:OrderedProduct? = null

        if(cursor != null){
//            if(cursor.moveToFirst()){
//                do {
                    var ProductName = cursor.getString(cursor.getColumnIndex(PRODUCT_NAME))
//                    var ProductDesc = cursor.getString(cursor.getColumnIndex(PRODUCT_DESC))
                    var ProductPhoto = cursor.getString(cursor.getColumnIndex(PRODUCT_PHOTO))
//                    var ProductID = cursor.getString(cursor.getColumnIndex(PRODUCT_ID)).toInt()
                    var ProductPrice = cursor.getString(cursor.getColumnIndex(PRODUCT_PRICE))
                    var city = cursor.getString(cursor.getColumnIndex(CUSTOMER_CITY))
                    var phone = cursor.getString(cursor.getColumnIndex(CUSTOMER_PHONE))
                    var secName = cursor.getString(cursor.getColumnIndex(CUSTOMER_SEC_NAME))
                    var delPrice = cursor.getString(cursor.getColumnIndex(CUSTOMER_DLIVERY_PRICE))
                    var total = cursor.getString(cursor.getColumnIndex(CUSTOMER_TOTAL_PRICE))
                    var totalProduct = cursor.getString(cursor.getColumnIndex(CUSTOMER_TOTAL_PRODUCT_PRICE))
                    var customerName = cursor.getString(cursor.getColumnIndex(CUSTOMER_NAME))

//                    Log.i("product", "ProductName: $ProductName, ProductDesc: $ProductDesc, ProductPhoto: $ProductPhoto" +
//                            "ProductID: $ProductID, ProductPrice: $ProductPrice")
                    product = OrderedProduct(ProductName, ProductPhoto,ProductPrice,customerName,
                        secName,city,phone,total,
                        delPrice,totalProduct)
                //}while (cursor.moveToNext())
            }
       // }

        cursor.close()
        db.close()
        return product
    }


    fun addOrder(order: OrderedProduct): Boolean{
        val db =  this.writableDatabase
        val values = ContentValues()
        values.put(PRODUCT_NAME, order.name)
        values.put(PRODUCT_PRICE, order.price)
        values.put(PRODUCT_PHOTO, order.thumbnail)
//        values.put(ORDER_ID, order.id)
        values.put(CUSTOMER_NAME, order.customerName)
        values.put(CUSTOMER_CITY, order.customerCity)
        values.put(CUSTOMER_SEC_NAME, order.customerSecName)
        values.put(CUSTOMER_ADDRESS,order.customerCity)
        values.put(CUSTOMER_PHONE, order.customerPhone)
        values.put(CUSTOMER_TOTAL_PRICE, order.totalPrice)
        values.put(CUSTOMER_DLIVERY_PRICE, order.deliveryPrice)
        values.put(CUSTOMER_TOTAL_PRODUCT_PRICE, order.totalProductPrice)

        val _success = db.insert(ORDER_TABLE, null, values)

        db.close()

        Log.i("InsertedID", "$_success")
        return (Integer.parseInt("$_success") != -1)
    }
    fun getAllProduct():List<Product>{
        var ProductList: ArrayList<Product> = arrayListOf()

        val db = readableDatabase
        val selectAllQuery = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(selectAllQuery, null)

        if(cursor != null){
            if(cursor.moveToFirst()){
                do {

                    var ProductName = cursor.getString(cursor.getColumnIndex(PRODUCT_NAME))
                    var ProductDesc = cursor.getString(cursor.getColumnIndex(PRODUCT_DESC))
                    var ProductPhoto = cursor.getString(cursor.getColumnIndex(PRODUCT_PHOTO))
                    var ProductID = cursor.getString(cursor.getColumnIndex(PRODUCT_ID)).toInt()
                    var ProductPrice = cursor.getString(cursor.getColumnIndex(PRODUCT_PRICE))

//                    Log.i("product", "ProductName: $ProductName, ProductDesc: $ProductDesc, ProductPhoto: $ProductPhoto" +
//                            "ProductID: $ProductID, ProductPrice: $ProductPrice")
                    val product = Product(ProductID, ProductName, ProductPhoto,  ProductPrice,ProductDesc  )
                    Log.i("product", product.toString())
                    (ProductList as ArrayList<Product>).add(product)
                }while (cursor.moveToNext())
            }
        }

        cursor.close()
        db.close()
        return ProductList
    }


    fun addProduct(product: Product): Boolean{
        val db =  this.writableDatabase
        val values = ContentValues()
        values.put(PRODUCT_NAME, product.name)
        values.put(PRODUCT_DESC, product.desc)
        values.put(PRODUCT_PRICE, product.price)
        values.put(PRODUCT_PHOTO, product.thumbnail)
        values.put(PRODUCT_ID, product.id)

        val _success = db.insert(TABLE_NAME, null, values)

        db.close()

        Log.i("InsertedID", "$_success")
        return (Integer.parseInt("$_success") != -1)
    }

    fun getAllOrder():List<OrderedProduct>{
        var ProductList: ArrayList<OrderedProduct> = arrayListOf()

//        val db = readableDatabase
//        val selectAllQuery = "SELECT * FROM $TABLE_NAME"
//        val cursor = db.rawQuery(selectAllQuery, null)
//


        val db = readableDatabase
        val selectAllQuery = "SELECT * FROM $ORDER_TABLE" //val selectAllQuery = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(selectAllQuery, null)

        if(cursor != null){
            if(cursor.moveToFirst()){
                do {

                    var ProductName = cursor.getString(cursor.getColumnIndex(PRODUCT_NAME))
//                    var ProductDesc = cursor.getString(cursor.getColumnIndex(PRODUCT_DESC))
                    var ProductPhoto = cursor.getString(cursor.getColumnIndex(PRODUCT_PHOTO))
//                    var ProductID = cursor.getString(cursor.getColumnIndex(PRODUCT_ID)).toInt()
                    var ProductPrice = cursor.getString(cursor.getColumnIndex(PRODUCT_PRICE))
                    var city = cursor.getString(cursor.getColumnIndex(CUSTOMER_CITY))
                    var phone = cursor.getString(cursor.getColumnIndex(CUSTOMER_PHONE))
                    var secName = cursor.getString(cursor.getColumnIndex(CUSTOMER_SEC_NAME))
                    var delPrice = cursor.getString(cursor.getColumnIndex(CUSTOMER_DLIVERY_PRICE))
                    var total = cursor.getString(cursor.getColumnIndex(CUSTOMER_TOTAL_PRICE))
                    var totalProduct = cursor.getString(cursor.getColumnIndex(CUSTOMER_TOTAL_PRODUCT_PRICE))
                    var customerName = cursor.getString(cursor.getColumnIndex(CUSTOMER_NAME))

//                    Log.i("product", "ProductName: $ProductName, ProductDesc: $ProductDesc, ProductPhoto: $ProductPhoto" +
//                            "ProductID: $ProductID, ProductPrice: $ProductPrice")
                    val product = OrderedProduct(ProductName, ProductPhoto,ProductPrice,customerName,
                        secName,city,phone,total,
                    delPrice,totalProduct)

                    Log.i("product", product.toString())
                    (ProductList as ArrayList<OrderedProduct>).add(product)
                }while (cursor.moveToNext())
            }
        }

        cursor.close()
        db.close()
        return ProductList
    }












    companion object {
        private val DB_NAME = "UsersDB"
        private val DB_VERSION = 1;
        private val TABLE_NAME = "users"
        private val ID = "id"
        private val PRODUCT_NAME = "ProductName"
        private val PRODUCT_DESC = "ProductDesc"
        private val PRODUCT_ID = "ProductId"
        private val PRODUCT_PRICE = "ProductPrice"
        private val PRODUCT_PHOTO = "ProductPhoto"

        private val ORDER_TABLE = "ordered"
//        private val ORDER_ID = "orderedId"
        private val CUSTOMER_NAME = "CustomerName"
        private val CUSTOMER_CITY = "CustomerCity"
        private val CUSTOMER_SEC_NAME = "CustomerSecName"
        private val CUSTOMER_ADDRESS = "CustomerAddress"
        private val CUSTOMER_PHONE = "CustomerAddress"
        private val CUSTOMER_TOTAL_PRICE = "CustomerTotalPrice"
        private val CUSTOMER_DLIVERY_PRICE = "CustomerDeliveryPrice"
        private val CUSTOMER_TOTAL_PRODUCT_PRICE = "CustomerProductPrice"


    }
}