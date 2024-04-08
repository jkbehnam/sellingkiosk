package com.example.myapplication.presentation.ui

import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.myapplication.databinding.FragmentFoodDetailsBinding
import java.util.concurrent.TimeUnit

class FoodDetailsFragment : Fragment() {
    private val args: com.example.myapplication.presentation.ui.FoodDetailsFragmentArgs by navArgs()
    lateinit var binding: FragmentFoodDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFoodDetailsBinding.inflate(inflater, container, false)
        val food = args.foodModel

        binding.detailIv.transitionName = food.imv

        val imageResId =
            requireContext().resources.getIdentifier(
                food.imv,
                "drawable",
                requireContext().packageName
            )
        binding.detailIv.setImageResource(imageResId)

        binding.detailTvFoodName.text = food.name
        binding.detailTvFoodTime.text = food.time.toString() + " دقیقه"
        binding.detailTvFoodPrice.text = food.price.toString()
        binding.detailTvFoodDesc.text = food.desc.toString()
        val animation =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        sharedElementEnterTransition = animation
        postponeEnterTransition(0, TimeUnit.MILLISECONDS)

        return binding.root
    }
}