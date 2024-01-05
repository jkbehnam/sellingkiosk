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
    }
}