package com.example.majika.ui.foodBank

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.majika.databinding.MenuCardBinding
import com.example.majika.models.Menu
import kotlinx.android.synthetic.main.menu_card.view.*


class MenuAdapter: RecyclerView.Adapter<MenuAdapter.Holder>(){
    private var menuList = arrayListOf<Menu>()
    class Holder(val view: View) : RecyclerView.ViewHolder(view)

    private lateinit var binding: MenuCardBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        binding = MenuCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding.root)
    }

    override fun getItemCount(): Int {
        return menuList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.view.menu_name.text = menuList[position].name
        holder.view.menu_price.text = menuList[position].currency.plus(" ").plus(menuList[position].price.toString())
        holder.view.menu_sold.text = menuList[position].sold.toString().plus(" Terjual!")
        holder.view.menu_desc.text = menuList[position].description
        holder.view.btn_plus.setOnClickListener {
            holder.view.quantity.visibility = View.VISIBLE
            holder.view.btn_minus.visibility = View.VISIBLE
            holder.view.quantity.text = (holder.view.quantity.text.toString().toInt() + 1).toString()
        }
        holder.view.btn_minus.setOnClickListener {
            if (holder.view.quantity.text.toString().toInt() == 1){
                holder.view.quantity.visibility = View.INVISIBLE
                holder.view.btn_minus.visibility = View.INVISIBLE
            }
            holder.view.quantity.text = (holder.view.quantity.text.toString().toInt() - 1).toString()
        }
    }

    fun showData(newMenuList: ArrayList<Menu>){
        menuList.clear()
        menuList.addAll(newMenuList)
        notifyDataSetChanged()
    }
}