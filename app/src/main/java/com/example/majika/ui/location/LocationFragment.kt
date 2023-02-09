package com.example.majika.ui.location

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.majika.databinding.FragmentLocationBinding
import com.example.majika.repository.Repository

class LocationFragment : Fragment() {

    private var _binding: FragmentLocationBinding? = null

    private val branchAdapter by lazy { BranchAdapter() }
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
    ): View {
        val repository = Repository()
        val viewModelFactory = LocationViewModelFactory(repository)
        val locationViewModel =
            ViewModelProvider(this, viewModelFactory)[LocationViewModel::class.java]
        locationViewModel.getBranches()
        locationViewModel.branchRes.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful) {
                response.body()?.data.let {
                    if (it != null) {
                        branchAdapter.setData(it)
                    }
                }
            }
        })

        _binding = FragmentLocationBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }    
    private fun setupRecyclerView() {
        binding.branchList.layoutManager = LinearLayoutManager(context)
        binding.branchList.adapter = branchAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}