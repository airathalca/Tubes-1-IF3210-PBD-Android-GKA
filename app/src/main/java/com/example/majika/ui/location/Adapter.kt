package com.example.majika.ui.location

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.majika.databinding.RecyclerRowBranchBinding
import com.example.majika.models.BranchData
import kotlinx.android.synthetic.main.recycler_row_branch.view.*

class BranchAdapter: RecyclerView.Adapter<BranchAdapter.Holder>(){
    private var branchList = arrayListOf<BranchData>()
    class Holder(val view: View) : RecyclerView.ViewHolder(view)

    private lateinit var binding: RecyclerRowBranchBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        binding = RecyclerRowBranchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding.root)
    }

    override fun getItemCount(): Int {
        return branchList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.view.branch_list_name.text = branchList[position].name
    }

    fun setData(newBranchList: ArrayList<BranchData>){
        branchList = newBranchList
        notifyDataSetChanged()
    }
}