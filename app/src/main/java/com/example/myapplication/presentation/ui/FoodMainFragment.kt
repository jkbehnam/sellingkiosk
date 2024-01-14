package com.example.myapplication.presentation.ui

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.domain.FoodRepository
import com.example.myapplication.presentation.adapter.MainFoodAdapter
import com.example.myapplication.presentation.adapter.OrderAdapter
import com.example.myapplication.R
import com.example.myapplication.data.DbModel.CategoryModel
import com.example.myapplication.data.FoodDatabase
import com.example.myapplication.data.DbModel.FoodModel
import com.example.myapplication.data.DbModel.OrderModel
import com.example.myapplication.presentation.viewmodel.FoodViewModel
import com.example.myapplication.presentation.viewmodel.ViewModelFactory
import com.example.myapplication.databinding.FragmentFoodMainBinding
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class FoodMainFragment : Fragment() {

    private lateinit var binding: FragmentFoodMainBinding
    private lateinit var foodAdapter: MainFoodAdapter
    private lateinit var orderAdapter: OrderAdapter
    private lateinit var foodViewModel: FoodViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFoodMainBinding.inflate(inflater, container, false)

        initViewModel()
        initUI()
        initListeners()
        initObservers()

        return binding.root
    }

    private fun initViewModel() {
        val foodDB = FoodDatabase.getInstance(requireContext())
        val foodDao = foodDB.foodDao()
        val foodRepository = FoodRepository(foodDao)
        val viewModelFactory = ViewModelFactory(foodRepository)
        foodViewModel =
            ViewModelProvider(requireActivity(), viewModelFactory).get(FoodViewModel::class.java)
    }

    private fun initUI() {
        initTabLayout()
        initRecyclerViews()
        initTransitionAnimation()
    }

    private fun initTabLayout() {
        for (i in 1..6) {
            binding.tablayout.addTab(binding.tablayout.newTab().setCustomView(R.layout.item_tab))
        }

        binding.lltab.viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                binding.lltab.viewTreeObserver.removeOnGlobalLayoutListener(this)
                val height = binding.lltab.height
                val layoutParams = LinearLayout.LayoutParams(height, height)
                binding.lltab.layoutParams = layoutParams
                binding.lltab.rotation = -90F
            }
        })
    }

    private fun initRecyclerViews() {
        foodAdapter = MainFoodAdapter(
            requireActivity(),
            mutableListOf(),
            mutableMapOf(),
            onclickListener = { food, iv ->
                onFoodItemClick(food, iv)
            },
            onaddlickListener = { food ->
                foodViewModel.addToOrder(food, CategoryModel(1, "", ""))
            }
        )

        val gridLayoutManager = GridLayoutManager(requireContext(), 1)
        binding.mainRclc.adapter = foodAdapter
        binding.mainRclc.layoutManager = gridLayoutManager

        orderAdapter = OrderAdapter(mutableListOf())
        val linearLayoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewOrderList.adapter = orderAdapter
        binding.recyclerViewOrderList.layoutManager = linearLayoutManager
    }

    private fun initTransitionAnimation() {
        val animation =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        sharedElementEnterTransition = animation
        postponeEnterTransition(5000, TimeUnit.MILLISECONDS)
        binding.mainRclc.doOnPreDraw {
            startPostponedEnterTransition()
        }
    }

    private fun initListeners() {
        binding.mainLlOrders.setOnClickListener {
            onOrdersLayoutClick()
        }

        binding.tablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                updateTabTextColor(tab, R.color.mainText)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                updateTabTextColor(tab, android.R.color.white)
            }

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    /*   private fun initObservers() {
           foodViewModel.foods.observe(viewLifecycleOwner, Observer {
               foodAdapter.setData(it)
           })

           foodViewModel.orders.observe(viewLifecycleOwner, Observer {
               updateOrdersUI(it)
           })
       }*/
    private fun initObservers() {
        /*  foodViewModel.foods.observe(viewLifecycleOwner, Observer {
              foodAdapter.setData(it)
          })*/

        viewLifecycleOwner.lifecycleScope.launch {
            foodViewModel.foods2.collect { foods ->
                foodAdapter.setData(foods)
            }

        }

        foodViewModel.orders.observe(requireActivity(), Observer {
            updateOrdersUI(it)
        })


    }

    private fun onFoodItemClick(food: FoodModel, iv: ImageView) {
        val directions: NavDirections =
            com.example.myapplication.presentation.ui.FoodMainFragmentDirections.actionFoodMainFragmentToFoodDetailsFragment(
                food
            )
        val extras = FragmentNavigatorExtras(iv to food.imv!!)
        findNavController().navigate(directions, extras)
    }

    private fun onOrdersLayoutClick() {
        orderAdapter.setData(foodViewModel.orders.value!!)
        binding.drawerLayout.openDrawer(GravityCompat.END)
    }

    private fun updateTabTextColor(tab: TabLayout.Tab, colorResId: Int) {
        tab.customView?.findViewById<TextView>(R.id.textView)?.setTextColor(
            ContextCompat.getColor(requireContext(), colorResId)
        )
    }

    private fun updateOrdersUI(orders: List<OrderModel>) {
        binding.mainTvOrderCount.text = orders.sumOf { it.count }.toString()
        binding.mainTvOrderPrice.text = orders.sumOf { it.final_price }.toString()
    }
}
