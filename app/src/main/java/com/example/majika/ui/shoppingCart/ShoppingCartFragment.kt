package com.example.majika.ui.shoppingCart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.majika.databinding.FragmentShoppingCartBinding
import com.example.majika.repository.*
import com.example.majika.room.*

class ShoppingCartFragment : Fragment() {

    private var _binding: FragmentShoppingCartBinding? = null
    private lateinit var cartViewModel: ShoppingCartViewModel

    private val cartAdapter by lazy { CartAdapter() }
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cartList.layoutManager = LinearLayoutManager(context)
        binding.cartList.adapter = cartAdapter
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
        // get all Cart items
        cartViewModel.listCart.observe(viewLifecycleOwner, Observer { cartList ->
            if (cartList != null) {
                //change list to ArrayList
                val cartArray = ArrayList(cartList)
                cartAdapter.setData(cartArray, cartViewModel)
            }
        })
        _binding = FragmentShoppingCartBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}