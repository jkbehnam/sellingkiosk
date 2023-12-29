package com.example.myapplication

import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.example.myapplication.databinding.FragmentFoodDetailsBinding
import java.util.concurrent.TimeUnit

class FoodDetailsFragment : Fragment() {
    private val args: FoodDetailsFragmentArgs by navArgs()
lateinit var binding:FragmentFoodDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentFoodDetailsBinding.inflate(inflater,container,false)
        binding.detailIv.transitionName = args.foodModel.imv

        val imageResId =
            requireContext().resources.getIdentifier(args.foodModel.imv, "drawable", requireContext().packageName)
        binding.detailIv.setImageResource(imageResId)

        val animation = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        sharedElementEnterTransition = animation
        postponeEnterTransition(200, TimeUnit.MILLISECONDS)
        // Inflate the layout for this fragment
        return binding.root
    }
}