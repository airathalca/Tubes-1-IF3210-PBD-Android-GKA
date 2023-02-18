package com.example.majika.ui.foodBank

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.majika.databinding.FragmentFoodBankBinding
import com.example.majika.repository.*
import com.example.majika.ui.shoppingCart.*
import com.example.majika.room.* 

class FoodBankFragment : Fragment() {

    private var _binding: FragmentFoodBankBinding? = null
    private lateinit var cartViewModel: ShoppingCartViewModel

    private val menuAdapter by lazy { MenuAdapter() }
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.menuList.layoutManager = LinearLayoutManager(context)
        binding.menuList.adapter = menuAdapter
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val cartDatabase = CartDatabase.getDatabase(requireContext())
        val cartRepository = CartRepository(cartDatabase)
        val cartModelFactory = ShoppingCartViewModelFactory(cartRepository)
        cartViewModel =
            ViewModelProvider(this, cartModelFactory)[ShoppingCartViewModel::class.java]
        val repository = Repository()
        val viewModelFactory = FoodBankViewModelFactory(repository)
        val foodBankViewModel =
            ViewModelProvider(this, viewModelFactory)[FoodBankViewModel::class.java]
        foodBankViewModel.getMenus()
        foodBankViewModel.menuRes.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful) {
                response.body()?.data.let {
                    if (it != null) {
                        menuAdapter.showData(it, cartViewModel)
                    }
                }
            }
        })

        _binding = FragmentFoodBankBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}