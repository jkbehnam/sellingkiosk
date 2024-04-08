package com.example.myapplication.presentation.ui

import  android.os.Bundle
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
import com.example.myapplication.presentation.adapter.MainFoodAdapter
import com.example.myapplication.presentation.adapter.OrderAdapter
import com.example.myapplication.R
import com.example.myapplication.data.DbModel.CategoryModel
import com.example.myapplication.data.DbModel.FoodModel
import com.example.myapplication.data.DbModel.OrderModel
import com.example.myapplication.presentation.viewmodel.FoodViewModel
import com.example.myapplication.presentation.viewmodel.ViewModelFactory
import com.example.myapplication.databinding.FragmentFoodMainBinding
import com.example.myapplication.domain.FoodRepositpry
import com.example.myapplication.presentation.di.DaggerAppComponent
import com.example.myapplication.presentation.di.RoomModule
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class FoodMainFragment : Fragment() {
    @Inject
    lateinit var foodRepositpry: FoodRepositpry
    private lateinit var binding: FragmentFoodMainBinding
    private lateinit var foodAdapter: MainFoodAdapter
    private lateinit var orderAdapter: OrderAdapter
    private lateinit var foodViewModel: FoodViewModel
    lateinit var selectedTab: CategoryModel
    lateinit var catgList: MutableList<CategoryModel>
    val orderList = listOf<OrderModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setRetainInstance(true);

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFoodMainBinding.inflate(inflater, container, false)

        DaggerAppComponent.builder().roomModule(RoomModule(requireContext())).build().inject(this)

        initViewModel()
        initUI()
        initListeners()
        initObservers()

        return binding.root
    }

    private fun initViewModel() {
        val viewModelFactory = ViewModelFactory(foodRepositpry)
        foodViewModel =
            ViewModelProvider(this, viewModelFactory).get(FoodViewModel::class.java)
    }

    private fun initUI() {
        initTabLayout()
        initRecyclerViews()
        initTransitionAnimation()
    }

    private fun initTabLayout() {
        catgList = mutableListOf<CategoryModel>()
        catgList.add(CategoryModel(1, "پر طرفدارها", R.drawable.bestseller))
        catgList.add(CategoryModel(2, "پیتزا", R.drawable.pizza))
        catgList.add(CategoryModel(3, "ساندویچ", R.drawable.sandwich))
        catgList.add(CategoryModel(4, "پیش غذا", R.drawable.salad))
        catgList.add(CategoryModel(5, "نوشیدنی", R.drawable.soda))

        catgList.reverse()

        for (catg in catgList) {

            var tab = binding.tablayout.newTab().setCustomView(R.layout.item_tab)
            tab.customView?.findViewById<TextView>(R.id.tab_tv)?.setText(catg.name)
            tab.customView?.findViewById<ImageView>(R.id.tab_iv)?.setImageResource(catg.img)
            binding.tablayout.addTab(tab)

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
            orderList,
            onclickListener = { food, iv ->
                onFoodItemClick(food, iv)
            },
            onaddlickListener = { food, plus ->
                foodViewModel.addToOrder(food, selectedTab, plus)
            }
        )

        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
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
                selectedTab = catgList.get(binding.tablayout.selectedTabPosition)
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {
                updateTabTextColor(tab, android.R.color.white)
            }

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
        binding.tablayout.getTabAt(2)?.select()
    }

    private fun initObservers() {

        viewLifecycleOwner.lifecycleScope.launch {
            foodViewModel.foods.collect { foods ->
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
        tab.customView?.findViewById<TextView>(R.id.tab_tv)?.setTextColor(
            ContextCompat.getColor(requireContext(), colorResId)
        )
    }

    private fun updateOrdersUI(orders: List<OrderModel>) {
        foodAdapter.setOrderd(orders)
        binding.mainTvOrderCount.text =
            MainActivity2.removedecimal(orders.sumOf { it.count }.toString())
        binding.mainTvOrderPrice.text = orders.sumOf { it.final_price }.toString()
    }
}
