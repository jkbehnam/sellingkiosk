package com.example.myapplication.Models

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Database(entities = [FoodModel::class], version = 5, exportSchema = false)
abstract class FoodDatabase : RoomDatabase() {
    abstract fun foodDao(): FoodDao

    companion object {
        private var instance: FoodDatabase? = null
        private var applicationContext: Context? = null

        fun getInstance(context: Context): FoodDatabase {
            applicationContext = context
            createDB()

            return instance!!
        }

        private fun createDB() {
            synchronized(FoodDatabase::class) {
                instance = Room.databaseBuilder(
                    applicationContext!!,
                    FoodDatabase::class.java,
                    "food_database"
                )
                    .addMigrations(FoodDatabase.MIGRATION_1_2,MIGRATION_2_3,MIGRATION_3_4,MIGRATION_4_5,MIGRATION_5_6)
                    .build()
            }
        }

        val MIGRATION_1_2 = object : Migration(0, 1) {
            override fun migrate(db: SupportSQLiteDatabase) {
                Log.d("FoodDatabase1", "Migration started1")

                // اجرای دستور SQL برای افزودن ستون جدید به جدول
              //  db.execSQL("ALTER TABLE food_table ADD testi TEXT NOT NULL DEFAULT ''")
            }
        }
        val MIGRATION_2_3 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                Log.d("FoodDatabase2", "Migration started2")

                // اجرای دستور SQL برای افزودن ستون جدید به جدول
              //  db.execSQL("ALTER TABLE food_table ADD testi TEXT NOT NULL DEFAULT ''")
            }
        }
        val MIGRATION_3_4 = object : Migration(2, 3) {
            override fun migrate(db: SupportSQLiteDatabase) {
                Log.d("FoodDatabase2", "Migration started3")

                // اجرای دستور SQL برای افزودن ستون جدید به جدول
                db.execSQL("ALTER TABLE food_table ADD COLUMN testi TEXT DEFAULT ''")

            }
        }
        val MIGRATION_4_5 = object : Migration(3, 4) {
            override fun migrate(db: SupportSQLiteDatabase) {

                Log.d("FoodDatabase2", "Migration started3")

                GlobalScope.launch(Dispatchers.IO) {
                    // اضافه کردن چند رکورد به دیتابیس
                    val foodDao = FoodDatabase.getInstance(applicationContext!!).foodDao()

                    // افزودن رکوردهای مورد نظر به دیتابیس
                    val foodList = listOf(
                        FoodModel(catgId = 0, name = " پیتزا چهارفاصل", imv = "pizza", ingredients =  "دوغ، گوجه‌فرنگی، پنیر، زیتون و ...", price =  "۱۰.۹۹ دلار"),
                        FoodModel(catgId = 0, name = "برگر گوشت دست ساز",imv = "burgure",ingredients = "گوشت گاو، پنیر، خیارشور، ریحان و ...", price ="۸.۹۹ دلار"),
                        FoodModel(catgId = 0, name = "سالاد", imv ="salad", ingredients ="سبزیجات تازه، خیار، گوجه‌فرنگی، مرغ گریل شده و ...", price ="۶.۹۹ دلار"),
                        FoodModel(catgId = 0, name = "سوشی", imv ="sandwich",ingredients = "برنج، ماهی تازه، آووکادو، نوری و ...",price = "۱۴.۹۹ دلار"),
                    )

                    foodDao.insertItems(foodList)
                }

                // اجرای دستور SQL برای افزودن ستون جدید به جدول

            }
        }
        val MIGRATION_5_6 = object : Migration(4, 5) {
            override fun migrate(db: SupportSQLiteDatabase) {

                Log.d("FoodDatabase2", "Migration started3")

                // اجرای دستور SQL برای افزودن ستون جدید به جدول
                db.execSQL("ALTER TABLE food_table ADD COLUMN new_column_name TEXT NOT NULL DEFAULT ''")

                // اضافه کردن چند رکورد به دیتابیس
                db.execSQL("INSERT INTO food_table (catgId, name, imv, ingredients, price) VALUES (0, 'پیتزا چهارفاصل', 'pizza', 'دوغ، گوجه‌فرنگی، پنیر، زیتون و ...', '۱۰.۹۹ دلار')")
                db.execSQL("INSERT INTO food_table (catgId, name, imv, ingredients, price) VALUES (0, 'برگر گوشت دست ساز', 'burgure', 'گوشت گاو، پنیر، خیارشور، ریحان و ...', '۸.۹۹ دلار')")
                db.execSQL("INSERT INTO food_table (catgId, name, imv, ingredients, price) VALUES (0, 'سالاد', 'salad', 'سبزیجات تازه، خیار، گوجه‌فرنگی، مرغ گریل شده و ...', '۶.۹۹ دلار')")
                db.execSQL("INSERT INTO food_table (catgId, name, imv, ingredients, price) VALUES (0, 'سوشی', 'sandwich', 'برنج، ماهی تازه، آووکادو، نوری و ...', '۱۴.۹۹ دلار')")

                // اجرای دستور SQL برای افزودن ستون جدید به جدول

            }
        }
    }
}

