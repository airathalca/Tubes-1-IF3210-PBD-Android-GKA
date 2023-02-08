package com.example.majika.ui.foodBank

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.majika.databinding.FragmentFoodBankBinding

class FoodBankFragment : Fragment() {

    private var _binding: FragmentFoodBankBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val FoodBankViewModel =
            ViewModelProvider(this).get(FoodBankViewModel::class.java)

        _binding = FragmentFoodBankBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textFoodBank
        FoodBankViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}