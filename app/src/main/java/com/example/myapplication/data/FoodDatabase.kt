package com.example.myapplication.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.myapplication.data.DbModel.FoodModel

@Database(entities = [FoodModel::class], version = 3, exportSchema = false)
abstract class FoodDatabase : RoomDatabase() {
    abstract fun foodDao(): FoodDao

    companion object {
        private var instance: FoodDatabase? = null
        private lateinit var applicationContext: Context

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
                    .addCallback(MyDatabaseInitializer(applicationContext))
                    .build()
            }
        }

        val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(db: SupportSQLiteDatabase) {

             /*   Log.d("FoodDatabase2", "Migration started3")
                db.execSQL("INSERT INTO food_table (catgId, name, imv, ingredients, price, testi) VALUES (0, 'سوشی', 'sandwich', 'برنج، ماهی تازه، آووکادو، نوری و ...', '۱۴.۹۹ دلار', 'f')")
                db.execSQL("INSERT INTO food_table (catgId, name, imv, ingredients, price, testi) VALUES (0, 'سالاد', 'salad', 'سبزیجات تازه، خیار، گوجه‌فرنگی، مرغ گریل شده و ...', '۶.۹۹ دلار', 'f')")
*/
                // اجرای دستور SQL برای افزودن ستون جدید به جدول

            }
        }
    }
}

