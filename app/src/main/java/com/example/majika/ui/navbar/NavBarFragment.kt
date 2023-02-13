package com.example.majika.ui.navbar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.example.majika.databinding.FragmentNavbarBinding
import com.example.majika.R

class NavBarFragment : Fragment() {

    private var _binding: FragmentNavbarBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNavbarBinding.inflate(inflater, container, false)

        val navController = requireActivity().findNavController(R.id.nav_host_fragment_activity_main)

        binding.navigationTwibbon.setOnClickListener {
            navController.navigate(R.id.navigation_twibbon)
        }

        binding.navigationLocation.setOnClickListener {
            navController.navigate(R.id.navigation_location)
        }

        binding.navigationFoodBank.setOnClickListener {
            navController.navigate(R.id.navigation_food_bank)
        }

        binding.navigationShoppingCart.setOnClickListener {
            navController.navigate(R.id.navigation_shopping_cart)
        }

        return binding.root
    }
}