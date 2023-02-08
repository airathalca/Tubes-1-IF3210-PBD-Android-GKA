package com.example.majika.ui.location

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.majika.databinding.RecyclerRowBranchBinding
import com.example.majika.models.BranchData
import com.example.majika.R

class BranchAdapter: RecyclerView.Adapter<BranchAdapter.Holder>(){

    class Holder(val view: View) : RecyclerView.ViewHolder(view)

    private var branchList = emptyList<BranchData>()
    private lateinit var binding: RecyclerRowBranchBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        binding = RecyclerRowBranchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding.root)
    }

    override fun getItemCount(): Int {
        return branchList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        var branch_name: TextView = holder.view.findViewById(R.id.branch_list_name)
        Log.i("branch", branchList[position].name)
        branch_name.text = branchList[position].name
    }

    fun setData(newBranchList: ArrayList<BranchData>){
        branchList = newBranchList
        Log.i("branch", branchList.toString())
        notifyDataSetChanged()
    }
}