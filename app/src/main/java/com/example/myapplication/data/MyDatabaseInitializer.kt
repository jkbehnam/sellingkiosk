package com.example.myapplication.data

import android.content.Context
import android.util.Log
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.myapplication.data.DbModel.FoodModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MyDatabaseInitializer(private val context: Context) :  RoomDatabase.Callback  () {
    override fun onCreate(db: SupportSQLiteDatabase) {
        val dao = FoodDatabase.getInstance(context).foodDao()
        CoroutineScope(Dispatchers.IO).launch {
            val foodList = listOf(
                FoodModel(catgId = 0, name = " پیتزا چهارفاصل", imv = "pizza", ingredients =  "دوغ، گوجه‌فرنگی، پنیر، زیتون و ...", price =  325.0),
                FoodModel(catgId = 0, name = "برگر گوشت دست ساز",imv = "burgure",ingredients = "گوشت گاو، پنیر، خیارشور، ریحان و ...", price =400.0),
                FoodModel(catgId = 0, name = "سالاد", imv ="salad", ingredients ="سبزیجات تازه، خیار، گوجه‌فرنگی، مرغ گریل شده و ...", price =223.0),
                FoodModel(catgId = 0, name = "سوشی", imv ="sandwich",ingredients = "برنج، ماهی تازه، آووکادو، نوری و ...",price = 600.0),
              )
            dao.insertItems(foodList)
            Log.d("MyDatabaseInitializer", "Inserted ${foodList.size} items.")

        }
    }
}