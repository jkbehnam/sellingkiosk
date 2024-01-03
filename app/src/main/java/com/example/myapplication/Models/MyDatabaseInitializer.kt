package com.example.myapplication.Models

import android.app.Application
import android.util.Log
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MyDatabaseInitializer(private val context: Application) :  RoomDatabase.Callback  () {
    override fun onCreate(db: SupportSQLiteDatabase) {
        val dao = FoodDatabase.getInstance(context).foodDao()
        CoroutineScope(Dispatchers.IO).launch {
            val foodList = listOf(
                FoodModel(catgId = 0, name = " پیتزا چهارفاصل", imv = "pizza", ingredients =  "دوغ، گوجه‌فرنگی، پنیر، زیتون و ...", price =  "۱۰.۹۹ دلار"),
                FoodModel(catgId = 0, name = "برگر گوشت دست ساز",imv = "burgure",ingredients = "گوشت گاو، پنیر، خیارشور، ریحان و ...", price ="۸.۹۹ دلار"),
                FoodModel(catgId = 0, name = "سالاد", imv ="salad", ingredients ="سبزیجات تازه، خیار، گوجه‌فرنگی، مرغ گریل شده و ...", price ="۶.۹۹ دلار"),
                FoodModel(catgId = 0, name = "سوشی", imv ="sandwich",ingredients = "برنج، ماهی تازه، آووکادو، نوری و ...",price = "۱۴.۹۹ دلار"),
              )
            dao.insertItems(foodList)
            Log.d("MyDatabaseInitializer", "Inserted ${foodList.size} items.")

        }
    }
}