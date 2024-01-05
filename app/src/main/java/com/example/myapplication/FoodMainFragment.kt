package com.example.myapplication

import android.graphics.Color
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.Models.CategoryModel
import com.example.myapplication.Models.FoodDatabase
import com.example.myapplication.Models.FoodModel
import com.example.myapplication.Models.OrderModel
import com.example.myapplication.databinding.FragmentFoodMainBinding
import com.google.android.material.tabs.TabLayout
import java.util.concurrent.TimeUnit

class FoodMainFragment : Fragment() {
    lateinit var binding: FragmentFoodMainBinding
    lateinit var foodAdapter: MainFoodAdapter
    lateinit var orderAdapter: OrderAdapter
    lateinit var foodViewModel: FoodViewModel
    lateinit var foodDB: FoodDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFoodMainBinding.inflate(inflater, container, false)

        foodDB = FoodDatabase.getInstance(requireContext())
        val fooddao = foodDB.foodDao()
        val foodRepository = FoodRepository(fooddao)
        val viewModelFactory = ViewModelFactory(foodRepository)
        foodViewModel =
            ViewModelProvider(requireActivity(), viewModelFactory).get(FoodViewModel::class.java)

        binding.tablayout.addTab(binding.tablayout.newTab().setCustomView(R.layout.item_tab))
        binding.tablayout.addTab(binding.tablayout.newTab().setCustomView(R.layout.item_tab))
        binding.tablayout.addTab(binding.tablayout.newTab().setCustomView(R.layout.item_tab))
        binding.tablayout.addTab(binding.tablayout.newTab().setCustomView(R.layout.item_tab))
        binding.tablayout.addTab(binding.tablayout.newTab().setCustomView(R.layout.item_tab))
        binding.tablayout.addTab(binding.tablayout.newTab().setCustomView(R.layout.item_tab))

        binding.lltab.viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                // حذف نظارت‌گر برای جلوگیری از فراخوانی تکراری
                binding.lltab.viewTreeObserver.removeOnGlobalLayoutListener(this)
                // دریافت عرض LinearLayout
                val height = binding.lltab.height
                val width = binding.lltab.width
                val layoutParams = LinearLayout.LayoutParams(height, height)
                binding.lltab.layoutParams = layoutParams
                binding.lltab.rotation = -90F

            }
        })
        // open drawer at start
        binding.mainLlOrders.setOnClickListener(View.OnClickListener {
            orderAdapter.setData(foodViewModel.orders.value!!)
            binding.drawerLayout.openDrawer(GravityCompat.END)

        })
        createRecyclerview(listOf<FoodModel>())
        createFactorRecyclerview(listOf<OrderModel>())
        foodViewModel.foods.observe(requireActivity(), Observer {

            foodAdapter.setData(it)
        })

        foodViewModel.orders.observe(requireActivity(), Observer {

            binding.mainTvOrderCount.text = it.sumOf { it.count }.toString()
            binding.mainTvOrderPrice.text = it.sumOf { it.final_price }.toString()

        })

        binding.tablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                var txt: TextView = tab.customView!!.findViewById(R.id.textView)
                txt.setTextColor(ContextCompat.getColor(context!!, R.color.mainText))
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                var txt: TextView = tab.customView!!.findViewById(R.id.textView)
                txt.setTextColor(Color.WHITE)

            }

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        val animation =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        sharedElementEnterTransition = animation
        postponeEnterTransition(5000, TimeUnit.MILLISECONDS)
        binding.mainRclc.doOnPreDraw {
            startPostponedEnterTransition()
        }
        // Inflate the layout for this fragment
        return binding.root

    }


    fun createRecyclerview(foodList: List<FoodModel>): Unit {

        foodAdapter =
            MainFoodAdapter(requireActivity(), foodList,mutableMapOf<Int, Double>(), onclickListener={ food: FoodModel, iv: ImageView ->

                Toast.makeText(requireContext(), (food as FoodModel).name, Toast.LENGTH_SHORT)
                    .show()
                val directions: NavDirections =
                    FoodMainFragmentDirections.actionFoodMainFragmentToFoodDetailsFragment(food)
                val extras = FragmentNavigatorExtras(
                    iv to food.imv!!
                )

                findNavController().navigate(directions, extras)
            }, { food: FoodModel ->
                foodViewModel.addToOrder(food, CategoryModel(1, "", ""))
            })

        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        binding.mainRclc.adapter = foodAdapter
        binding.mainRclc.layoutManager = gridLayoutManager


    }
    fun createFactorRecyclerview(orderlist: List<OrderModel>): Unit {

        orderAdapter = OrderAdapter( orderlist)

        val linearLayoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewOrderList.adapter = orderAdapter
        binding.recyclerViewOrderList.layoutManager = linearLayoutManager


    }

}