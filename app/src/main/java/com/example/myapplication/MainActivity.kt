package com.example.myapplication

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.ViewTreeObserver
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Models.FoodMainModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener

class MainActivity : BaseActivity() {
    lateinit var tab:TabLayout
    lateinit var ll:LinearLayout
    lateinit var llmain:LinearLayout
    lateinit var rcycMain:RecyclerView
    lateinit var foodAdapter:MainFoodAdapter
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
            FoodMainModel(" پیتزا چهارفاصل", "pizza", "دوغ، گوجه‌فرنگی، پنیر، زیتون و ...", "۱۰.۹۹ دلار"),
            FoodMainModel("برگر گوشت دست ساز", "burgure", "گوشت گاو، پنیر، خیارشور، ریحان و ...", "۸.۹۹ دلار"),
            FoodMainModel("سالاد", "salad", "سبزیجات تازه، خیار، گوجه‌فرنگی، مرغ گریل شده و ...", "۶.۹۹ دلار"),
            FoodMainModel("پاستا", "salad", "ماکارونی، گوشت قرمز، سس گوجه‌فرنگی و ...", "۱۲.۹۹ دلار"),
            FoodMainModel("سوشی", "sandwich", "برنج، ماهی تازه، آووکادو، نوری و ...", "۱۴.۹۹ دلار"),
            FoodMainModel("پیتزا", "pizza", "دوغ، گوجه‌فرنگی، پنیر، زیتون و ...", "۱۰.۹۹ دلار"),
            FoodMainModel("برگر", "burgure", "گوشت گاو، پنیر، خیارشور، ریحان و ...", "۸.۹۹ دلار"),
            FoodMainModel("سالاد", "salad", "سبزیجات تازه، خیار، گوجه‌فرنگی، مرغ گریل شده و ...", "۶.۹۹ دلار"),
            FoodMainModel("پیتزا", "pizza", "دوغ، گوجه‌فرنگی، پنیر، زیتون و ...", "۱۰.۹۹ دلار"),
            FoodMainModel("برگر", "burgure", "گوشت گاو، پنیر، خیارشور، ریحان و ...", "۸.۹۹ دلار"),
            FoodMainModel("سالاد", "salad", "سبزیجات تازه، خیار، گوجه‌فرنگی، مرغ گریل شده و ...", "۶.۹۹ دلار"),
        )
        foodAdapter= MainFoodAdapter(this,foodList)
        val gridLayoutManager=GridLayoutManager(this,2)
        rcycMain.adapter=foodAdapter
        rcycMain.layoutManager=gridLayoutManager

        tab.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
             var txt:TextView=  tab.customView!!.findViewById(R.id.textView)
                txt.setTextColor(ContextCompat.getColor(baseContext,R.color.mainText))

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