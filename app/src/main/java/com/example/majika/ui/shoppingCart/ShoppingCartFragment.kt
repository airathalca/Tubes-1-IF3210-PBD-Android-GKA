package com.example.majika.ui.shoppingCart

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.majika.PaymentActivity
import com.example.majika.databinding.FragmentShoppingCartBinding
import com.example.majika.repository.*
import com.example.majika.room.*
import kotlinx.android.synthetic.main.fragment_shopping_cart.*

class ShoppingCartFragment : Fragment() {
    private var _binding: FragmentShoppingCartBinding? = null
    private val cartAdapter by lazy { CartAdapter() }
    private val binding get() = _binding!!
    private var totalPrice = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cartList.layoutManager = LinearLayoutManager(context)
        binding.cartList.adapter = cartAdapter
        checkout_button.setOnClickListener {
            val intent = Intent(requireContext(), PaymentActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShoppingCartBinding.inflate(inflater, container, false)
        val cartDatabase = CartDatabase.getDatabase(requireContext())
        val cartRepository = CartRepository(cartDatabase)
        val cartModelFactory = ShoppingCartViewModelFactory(cartRepository)
        val cartViewModel =
            ViewModelProvider(this, cartModelFactory)[ShoppingCartViewModel::class.java]
        cartViewModel.listCart.observe(viewLifecycleOwner) { cartList ->
            if (cartList != null) {
                val cartArray = ArrayList(cartList)
                cartAdapter.setData(cartArray, cartViewModel)
                totalPrice = 0
                for (i in cartArray) {
                    totalPrice += i.price * i.quantity
                }
                binding.totalPrice.text = totalPrice.toString()
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}