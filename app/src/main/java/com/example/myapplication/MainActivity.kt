package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.LinearLayout
import android.widget.TableLayout
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    lateinit var tab:TabLayout
    lateinit var ll:LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tab=findViewById(R.id.tablayout)
        ll=findViewById(R.id.lltab)

        tab.addTab(tab.newTab().setCustomView(R.layout.item_tab))
        tab.addTab(tab.newTab().setCustomView(R.layout.item_tab))
        tab.addTab(tab.newTab().setCustomView(R.layout.item_tab))
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
        // تنظیم عرض لایه برابر با ارتفاع
      //  val layoutParams = LinearLayout.LayoutParams(1500, 1500)
      //  ll.layoutParams = layoutParams


    }
}