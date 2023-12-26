package com.example.myapplication

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.ViewTreeObserver
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener

class MainActivity : BaseActivity() {
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

        tab.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
             var txt:TextView=  tab.customView!!.findViewById(R.id.textView)
                txt.setTextColor(Color.GRAY)

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