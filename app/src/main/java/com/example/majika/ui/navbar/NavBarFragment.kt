package com.example.majika.ui.navbar

import android.app.ActionBar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.example.majika.MainActivity
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

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = requireActivity().findNavController(R.id.nav_host_fragment_activity_main)

        // If user click back on android, title and navigation will change
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navigation_twibbon -> {
                    (activity as AppCompatActivity).supportActionBar?.title = "Twibbon"
                    binding.navigationTwibbon.isSelected = true
                    binding.navigationLocation.isSelected = false
                    binding.navigationFoodBank.isSelected = false
                    binding.navigationShoppingCart.isSelected = false
                }
                R.id.navigation_location -> {
                    (activity as AppCompatActivity).supportActionBar?.title = "Cabang Restoran"
                    binding.navigationTwibbon.isSelected = false
                    binding.navigationLocation.isSelected = true
                    binding.navigationFoodBank.isSelected = false
                    binding.navigationShoppingCart.isSelected = false
                }
                R.id.navigation_food_bank -> {
                    (activity as AppCompatActivity).supportActionBar?.title = "Daftar Menu"
                    binding.navigationTwibbon.isSelected = false
                    binding.navigationLocation.isSelected = false
                    binding.navigationFoodBank.isSelected = true
                    binding.navigationShoppingCart.isSelected = false
                }
                R.id.navigation_shopping_cart -> {
                    (activity as AppCompatActivity).supportActionBar?.title = "Keranjang"
                    binding.navigationTwibbon.isSelected = false
                    binding.navigationLocation.isSelected = false
                    binding.navigationFoodBank.isSelected = false
                    binding.navigationShoppingCart.isSelected = true
                }
            }
        }

        binding.navigationTwibbon.setOnClickListener {
            (activity as AppCompatActivity).supportActionBar?.title = "Twibbon"

            binding.navigationTwibbon.isSelected = true
            binding.navigationLocation.isSelected = false
            binding.navigationFoodBank.isSelected = false
            binding.navigationShoppingCart.isSelected = false
            navController.navigate(R.id.navigation_twibbon)
        }

        binding.navigationLocation.setOnClickListener {
            (activity as AppCompatActivity).supportActionBar?.title = "Cabang Restoran"

            binding.navigationTwibbon.isSelected = false
            binding.navigationLocation.isSelected = true
            binding.navigationFoodBank.isSelected = false
            binding.navigationShoppingCart.isSelected = false
            navController.navigate(R.id.navigation_location)
        }

        binding.navigationFoodBank.setOnClickListener {
            (activity as AppCompatActivity).supportActionBar?.title = "Daftar Menu"
            binding.navigationTwibbon.isSelected = false
            binding.navigationLocation.isSelected = false
            binding.navigationFoodBank.isSelected = true
            binding.navigationShoppingCart.isSelected = false
            navController.navigate(R.id.navigation_food_bank)
        }

        binding.navigationShoppingCart.setOnClickListener {
            (activity as AppCompatActivity).supportActionBar?.title = "Keranjang"

            binding.navigationTwibbon.isSelected = false
            binding.navigationLocation.isSelected = false
            binding.navigationFoodBank.isSelected = false
            binding.navigationShoppingCart.isSelected = true
            navController.navigate(R.id.navigation_shopping_cart)
        }
    }
}