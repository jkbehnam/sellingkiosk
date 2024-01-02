package com.example.myapplication

import android.graphics.Color
import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.Models.FoodModel
import com.example.myapplication.databinding.FragmentFoodMainBinding
import com.google.android.material.tabs.TabLayout

class FoodMainFragment : Fragment() {
    lateinit var binding: FragmentFoodMainBinding
    lateinit var foodAdapter: MainFoodAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFoodMainBinding.inflate(inflater, container, false)
       /* tab=findViewById(R.id.tablayout)
        ll=findViewById(R.id.lltab)
        llmain=findViewById(R.id.llmain)*/
        val animation = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        sharedElementEnterTransition = animation

        binding.tablayout.addTab(binding.tablayout.newTab().setCustomView(R.layout.item_tab))
        binding.tablayout.addTab(binding.tablayout.newTab().setCustomView(R.layout.item_tab))
        binding.tablayout.addTab(binding.tablayout.newTab().setCustomView(R.layout.item_tab))
        binding.tablayout.addTab(binding.tablayout.newTab().setCustomView(R.layout.item_tab))
        binding.tablayout.addTab(binding.tablayout.newTab().setCustomView(R.layout.item_tab))
        binding.tablayout.addTab(binding.tablayout.newTab().setCustomView(R.layout.item_tab))



        binding.lltab.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                // حذف نظارت‌گر برای جلوگیری از فراخوانی تکراری
                binding.lltab.viewTreeObserver.removeOnGlobalLayoutListener(this)
                // دریافت عرض LinearLayout
                val height = binding.lltab.height
                val width = binding.lltab.width
                // تنظیم طول LinearLayout برابر با عرض
                val layoutParams = LinearLayout.LayoutParams(height,height)
                binding.lltab.layoutParams = layoutParams
                binding.lltab.rotation= -90F

            }
        })

       // postponeEnterTransition()
       /* binding.mainRclc.doOnPreDraw {
            startPostponedEnterTransition()
        }*/
        val foodList = listOf(
            FoodModel(0,0," پیتزا چهارفاصل", "pizza", "دوغ، گوجه‌فرنگی، پنیر، زیتون و ...", "۱۰.۹۹ دلار"),
            FoodModel(1,0,"برگر گوشت دست ساز", "burgure", "گوشت گاو، پنیر، خیارشور، ریحان و ...", "۸.۹۹ دلار"),
            FoodModel(2,0,"سالاد", "salad", "سبزیجات تازه، خیار، گوجه‌فرنگی، مرغ گریل شده و ...", "۶.۹۹ دلار"),
            FoodModel(3,0,"پاستا", "salad", "ماکارونی، گوشت قرمز، سس گوجه‌فرنگی و ...", "۱۲.۹۹ دلار"),
            FoodModel(4,0,"سوشی", "sandwich", "برنج، ماهی تازه، آووکادو، نوری و ...", "۱۴.۹۹ دلار"),
            FoodModel(5,0,"پیتزا", "pizza", "دوغ، گوجه‌فرنگی، پنیر، زیتون و ...", "۱۰.۹۹ دلار"),
            FoodModel(0,0,"برگر", "burgure", "گوشت گاو، پنیر، خیارشور، ریحان و ...", "۸.۹۹ دلار"),
            FoodModel(0,0,"سالاد", "salad", "سبزیجات تازه، خیار، گوجه‌فرنگی، مرغ گریل شده و ...", "۶.۹۹ دلار"),
            FoodModel(0,0,"پیتزا", "pizza", "دوغ، گوجه‌فرنگی، پنیر، زیتون و ...", "۱۰.۹۹ دلار"),
            FoodModel(0,0,"برگر", "burgure", "گوشت گاو، پنیر، خیارشور، ریحان و ...", "۸.۹۹ دلار"),
            FoodModel(0,0,"سالاد", "salad", "سبزیجات تازه، خیار، گوجه‌فرنگی، مرغ گریل شده و ...", "۶.۹۹ دلار"),
        )
        foodAdapter= MainFoodAdapter(requireActivity(),foodList) { food:FoodModel,iv: ImageView ->

            Toast.makeText(requireContext(),(food as FoodModel).name, Toast.LENGTH_SHORT).show()
            val directions: NavDirections =
                FoodMainFragmentDirections.actionFoodMainFragmentToFoodDetailsFragment(food)
            val extras = FragmentNavigatorExtras(
                iv to food.imv!!
            )
            findNavController().navigate(directions,extras)
        }

        val gridLayoutManager= GridLayoutManager(requireContext(),2)
        binding.mainRclc.adapter=foodAdapter
        binding.mainRclc.layoutManager=gridLayoutManager

        binding.tablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                var txt: TextView =  tab.customView!!.findViewById(R.id.textView)
                txt.setTextColor(ContextCompat.getColor(context!!, R.color.mainText))

                //do stuff here
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                var txt: TextView =  tab.customView!!.findViewById(R.id.textView)
                txt.setTextColor(Color.WHITE)

            }
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })






        // Inflate the layout for this fragment
        return binding.root

    }

}