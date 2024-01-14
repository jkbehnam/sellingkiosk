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
                FoodModel(catgId = 2, name = "پیتزا مخصوص", imv = "pizza_special", ingredients = "مرغ، قارچ، پنیر، فلفل دلمه و ...", price = 350.0, time = 10, desc = "پیتزای مخصوص با ترکیب هنرمندانه از مرغ، قارچ، پنیر، فلفل دلمه و ... تهیه می\u200Cشود. این پیتزا با طعم منحصر به فرد و مواد اولیه تازه، یک تجربه لذت\u200Cبخش برای دلچسبی فراهم می\u200Cکند. مرغ آبپز شده، قارچ تازه و پنیر خامه\u200Cای به همراه فلفل دلمه، به هر لقمه طعم ویژه\u200Cای می\u200Cبخشند. این پیتزا یک گزینه متفاوت و خوشمزه برای لذت بردن از یک وعده شام یا میان\u200Cوعده است."),
                FoodModel(catgId = 2, name = "پیتزا مارگاریتا", imv = "pizza_margherita", ingredients = "سس گوجه‌فرنگی، پنیر موتزارلا، گل فلفل و ...", price = 300.0, time = 10, desc = "پیتزا مارگاریتا با ترکیب سس گوجه\u200Cفرنگی، پنیر موتزارلا، و گل فلفل یک آرایه از طعم و ظاهر زیبا را در یک پیتزا ساده و لذیذ ارائه می\u200Cدهد. سس گوجه\u200Cفرنگی با طعم معتدل و تازه به همراه پنیر موتزارلا که ذوب شده و خوشمزه شده، به این پیتزا طعم ویژه\u200Cای می\u200Cبخشد. گل فلفل به عنوان تزئینی زیبا و مضافتی، این پیتزا را به یک انتخاب عالی برای لذت بردن از یک وعده خوشمزه و خاص تبدیل کرده است."),
                FoodModel(catgId = 2, name = "پیتزا آلا کاربنارا", imv = "pizza_carbonara", ingredients = "سس کربنارا، گوجه‌فرنگی، ژامبون، پنیر پارمزان و ...", price = 380.0, time = 10, desc = "پیتزا آلا کاربنارا با ترکیب منحصر به فرد از سس کربنارا، گوجه\u200Cفرنگی، ژامبون، و پنیر پارمزان، یک تجربه گرم و خوشمزه از طعم و عطر آشپزی ایتالیایی ارائه می\u200Cدهد. سس کربنارا با ترکیب گرم و خامه\u200Cای از طعم غنی به پیتزا افزوده شده و همراه با گوجه\u200Cفرنگی و ژامبون، ترکیبی خوشمزه از مواد اولیه را فراهم می\u200Cکند. پنیر پارمزان به عنوان افزونه\u200Cای شور و خوشمزه، این پیتزا را به یک گزینه لذت\u200Cبخش برای دلچسبی در سفر به دنیای طعم\u200Cهای ایتالیایی تبدیل می\u200Cکند."),
                FoodModel(catgId = 2, name = "پیتزا زنجبیلی", imv = "pizza_ginger", ingredients = "سس زنجبیلی، مرغ، قارچ، پنیر موتزارلا و ...", price = 360.0, time = 10, desc = ""),
                FoodModel(catgId = 2, name = "پیتزا مزه‌دار", imv = "pizza_tasty", ingredients = "سس مخلوط، مرغ، قارچ، کلم پورتوگالی و ...", price = 370.0, time = 10, desc = ""),
                FoodModel(catgId = 2, name = "پیتزا سبزیجات", imv = "pizza_vegetarian", ingredients = "سس گوجه‌فرنگی، پنیر، فلفل دلمه، زیتون، قارچ و ...", price = 320.0, time = 10, desc = ""),
                FoodModel(catgId = 2, name = "پیتزا آلفردو", imv = "pizza_alfredo", ingredients = "سس آلفردو، مرغ، قارچ، پنیر پارمزان و ...", price = 400.0, time = 10, desc = "")

                    ,
                FoodModel(catgId = 0, name = "برگر گوشت دست ساز",imv = "burgure",ingredients = "گوشت گاو، پنیر، خیارشور، ریحان و ...", price =400.0, time = 10, desc = ""),
                FoodModel(catgId = 0, name = "سالاد", imv ="salad", ingredients ="سبزیجات تازه، خیار، گوجه‌فرنگی، مرغ گریل شده و ...", price =223.0, time = 10, desc = ""),
                FoodModel(catgId = 0, name = "سوشی", imv ="sandwich",ingredients = "برنج، ماهی تازه، آووکادو، نوری و ...",price = 600.0, time = 10, desc = ""),
              )
            dao.insertItems(foodList)
            Log.d("MyDatabaseInitializer", "Inserted ${foodList.size} items.")

        }
    }
}