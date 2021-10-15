package com.srmstudios.jungsoomarket.ui.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.srmstudios.jungsoomarket.R
import com.srmstudios.jungsoomarket.data.database.entity.CartItem
import com.srmstudios.jungsoomarket.data.database.entity.CartItemJoinProduct
import com.srmstudios.jungsoomarket.databinding.CartItemBinding
import com.srmstudios.jungsoomarket.util.loadHttpsUrl

class CartAdapter(private val onProductRemoveListener: (CartItemJoinProduct) -> Unit): ListAdapter<CartItemJoinProduct,RecyclerView.ViewHolder>(CartDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CartViewHolder.from(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val cartItem = getItem(position)
        when(holder){
            is CartViewHolder -> {
                holder.bind(cartItem)
                holder.binding.imgDelete.setOnClickListener {
                    onProductRemoveListener(cartItem)
                }
            }
        }
    }

    class CartViewHolder(val binding: CartItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(cartItem: CartItemJoinProduct){
            binding.apply {
                imgProduct.loadHttpsUrl(cartItem.productThumbnail)
                txtName.text = cartItem.productName
                txtPrice.text = cartItem.productPrice
                txtQuantity.apply {
                    text = "${context.getString(R.string.quantity_with_colon)} ${cartItem.productQuantity}"
                }
            }
        }

        companion object {
            fun from(parent: ViewGroup) =
                CartViewHolder(CartItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
        }
    }

    object CartDiffUtil: DiffUtil.ItemCallback<CartItemJoinProduct>(){
        override fun areItemsTheSame(oldItem: CartItemJoinProduct, newItem: CartItemJoinProduct) =
            oldItem.cartItemId == newItem.cartItemId

        override fun areContentsTheSame(oldItem: CartItemJoinProduct, newItem: CartItemJoinProduct) =
            oldItem == newItem
    }
}