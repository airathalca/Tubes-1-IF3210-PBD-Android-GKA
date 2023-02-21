package com.example.majika.ui.shoppingCart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.majika.databinding.CartCardBinding
import com.example.majika.room.CartEntity
import kotlinx.android.synthetic.main.cart_card.view.*

class CartAdapter: RecyclerView.Adapter<CartAdapter.Holder>() {
    class Holder(val view: View) : RecyclerView.ViewHolder(view)

    private lateinit var viewModel: ShoppingCartViewModel
    private lateinit var binding: CartCardBinding
    private var cartList = arrayListOf<CartEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        binding = CartCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding.root)
    }

    override fun getItemCount(): Int {
        return cartList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.view.cart_name.text = cartList[position].item
        holder.view.cart_price.text = cartList[position].price.toString()
        holder.view.item_quantity.text = cartList[position].quantity.toString()
        holder.view.plus_cart_button.setOnClickListener {
            val quantityNow = holder.view.item_quantity.text.toString().toInt() + 1
            val newCart = CartEntity(cartList[position].item, cartList[position].price, quantityNow)
            viewModel.updateCart(newCart)
            holder.view.item_quantity.text = quantityNow.toString()
        }
        holder.view.minus_cart_button.setOnClickListener {
            val quantityNow = holder.view.item_quantity.text.toString().toInt() - 1
            if (quantityNow == 0) {
                val newCart = CartEntity(cartList[position].item, cartList[position].price, 1)
                viewModel.deleteCart(newCart)
                val pos = holder.adapterPosition
                cartList.removeAt(pos)
                notifyItemRemoved(pos)
                notifyItemRangeChanged(pos, cartList.size)
            } else {
                val newCart = CartEntity(cartList[position].item, cartList[position].price, quantityNow)
                viewModel.updateCart(newCart)
                holder.view.item_quantity.text = quantityNow.toString()
            }
        }
    }

    fun setData(list: ArrayList<CartEntity>, cartViewModel: ShoppingCartViewModel) {
        viewModel = cartViewModel
        cartList = list
        notifyDataSetChanged()
    }

}