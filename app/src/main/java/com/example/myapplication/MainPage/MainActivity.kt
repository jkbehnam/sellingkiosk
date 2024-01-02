package com.example.myapplication.MainPage

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.BaseActivity
import com.example.myapplication.MainFoodAdapter
import com.example.myapplication.Models.FoodModel
import com.example.myapplication.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener


class MainActivity : BaseActivity() {
    lateinit var tab:TabLayout
    lateinit var ll:LinearLayout
    lateinit var llmain:LinearLayout
    lateinit var rcycMain:RecyclerView
    lateinit var foodAdapter: MainFoodAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tab=findViewById(R.id.tablayout)
        ll=findViewById(R.id.lltab)
        llmain=findViewById(R.id.llmain)

        tab.addTab(tab.newTab().setCustomView(R.layout.item_tab))
        tab.addTab(tab.newTab().setCustomView(R.layout.item_tab))
        tab.addTab(tab.newTab().setCustomView(R.layout.item_tab))
        tab.addTab(tab.newTab().setCustomView(R.layout.item_tab))
        tab.addTab(tab.newTab().setCustomView(R.layout.item_tab))
        tab.addTab(tab.newTab().setCustomView(R.layout.item_tab))



        ll.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                // حذف نظارت‌گر برای جلوگیری از فراخوانی تکراری
                ll.viewTreeObserver.removeOnGlobalLayoutListener(this)
                // دریافت عرض LinearLayout
                val height = ll.height
                val width = ll.width
                // تنظیم طول LinearLayout برابر با عرض
                val layoutParams = LinearLayout.LayoutParams(height,height)
                ll.layoutParams = layoutParams
                ll.rotation= -90F

            }
        })

        rcycMain=findViewById(R.id.main_rclc)

        val foodList = listOf(
            FoodModel(0,0," پیتزا چهارفاصل", "pizza", "دوغ، گوجه‌فرنگی، پنیر، زیتون و ...", "۱۰.۹۹ دلار"),
            FoodModel(1,0,"برگر گوشت دست ساز", "burgure", "گوشت گاو، پنیر، خیارشور، ریحان و ...", "۸.۹۹ دلار"),
            FoodModel(2,0,"سالاد", "salad", "سبزیجات تازه، خیار، گوجه‌فرنگی، مرغ گریل شده و ...", "۶.۹۹ دلار"),
            FoodModel(3,0,"پاستا", "salad", "ماکارونی، گوشت قرمز، سس گوجه‌فرنگی و ...", "۱۲.۹۹ دلار"),
            FoodModel(4,0,"سوشی", "sandwich", "برنج، ماهی تازه، آووکادو، نوری و ...", "۱۴.۹۹ دلار"),
            FoodModel(0,0,"پیتزا", "pizza", "دوغ، گوجه‌فرنگی، پنیر، زیتون و ...", "۱۰.۹۹ دلار"),
            FoodModel(0,0,"برگر", "burgure", "گوشت گاو، پنیر، خیارشور، ریحان و ...", "۸.۹۹ دلار"),
            FoodModel(0,0,"سالاد", "salad", "سبزیجات تازه، خیار، گوجه‌فرنگی، مرغ گریل شده و ...", "۶.۹۹ دلار"),
            FoodModel(0,0,"پیتزا", "pizza", "دوغ، گوجه‌فرنگی، پنیر، زیتون و ...", "۱۰.۹۹ دلار"),
            FoodModel(0,0,"برگر", "burgure", "گوشت گاو، پنیر، خیارشور، ریحان و ...", "۸.۹۹ دلار"),
            FoodModel(0,0,"سالاد", "salad", "سبزیجات تازه، خیار، گوجه‌فرنگی، مرغ گریل شده و ...", "۶.۹۹ دلار"),
        )
        foodAdapter= MainFoodAdapter(this,foodList) { food:FoodModel,iv:ImageView ->

                Toast.makeText(this,(food as FoodModel).name, Toast.LENGTH_SHORT).show()

        }

        val gridLayoutManager=GridLayoutManager(this,1)
        rcycMain.adapter=foodAdapter
        rcycMain.layoutManager=gridLayoutManager

        tab.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
             var txt:TextView=  tab.customView!!.findViewById(R.id.textView)
                txt.setTextColor(ContextCompat.getColor(baseContext, R.color.mainText))

                //do stuff here
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                var txt:TextView=  tab.customView!!.findViewById(R.id.textView)
                txt.setTextColor(Color.WHITE)

            }
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

    }
}