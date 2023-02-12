package com.example.majika.ui.location

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.majika.databinding.BranchCardBinding
import com.example.majika.models.Branch
import kotlinx.android.synthetic.main.branch_card.view.*


class BranchAdapter: RecyclerView.Adapter<BranchAdapter.Holder>(){
    private var branchList = arrayListOf<Branch>()
    class Holder(val view: View) : RecyclerView.ViewHolder(view)

    private lateinit var binding: BranchCardBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        binding = BranchCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding.root)
    }

    override fun getItemCount(): Int {
        return branchList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.view.branch_name.text = branchList[position].name
        holder.view.branch_address.text = branchList[position].address
        holder.view.branch_contact.text = branchList[position].contact_person.plus(" - ").plus(branchList[position].phone_number)
        holder.view.button_maps.setOnClickListener {
            val gmmIntentUri = Uri.parse("geo:${branchList[position].latitude},${branchList[position].longitude}?q=${branchList[position].latitude},${branchList[position].longitude}(${branchList[position].name})")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(holder.view.context, mapIntent, null)
        }
    }

    fun showData(newBranchList: ArrayList<Branch>){
        var sortedList = newBranchList.sortedWith(compareBy { it.name })
        branchList.clear()
        branchList.addAll(sortedList)
        notifyDataSetChanged()
    }
}