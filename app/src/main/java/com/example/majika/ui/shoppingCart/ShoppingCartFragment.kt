package com.example.majika.ui.shoppingCart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.majika.databinding.FragmentShoppingCartBinding

class ShoppingCartFragment : Fragment() {

    private var _binding: FragmentShoppingCartBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val shoppingCartViewModel =
            ViewModelProvider(this).get(ShoppingCartViewModel::class.java)

        _binding = FragmentShoppingCartBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textShoppingCart
        shoppingCartViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}