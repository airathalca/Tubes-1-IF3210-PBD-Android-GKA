package com.example.majika.ui.location

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.majika.databinding.FragmentLocationBinding
import com.example.majika.models.BranchData
import com.example.majika.repository.Repository

class LocationFragment : Fragment() {

    private var _binding: FragmentLocationBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val repository = Repository()
        val viewModelFactory = LocationViewModelFactory(repository)
        val locationViewModel =
            ViewModelProvider(this, viewModelFactory).get(LocationViewModel::class.java)
        locationViewModel.getBranches()
        locationViewModel.branchRes.observe(this, Observer { response ->
            if (response.isSuccessful) {
                response.body()?.data.let {
                    if (it != null) {
                        val branchAdapter = BranchAdapter()
                        branchAdapter.setData(it as ArrayList<BranchData>)
                    }
                }
            }
        })

        _binding = FragmentLocationBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }    
    private fun setupRecyclerView() {
        binding.branchList.adapter = BranchAdapter()
        binding.branchList.layoutManager = LinearLayoutManager(context)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}