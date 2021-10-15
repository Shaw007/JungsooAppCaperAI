package com.srmstudios.jungsoomarket.ui.products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.srmstudios.jungsoomarket.data.database.entity.Product
import com.srmstudios.jungsoomarket.databinding.ProductItemBinding
import com.srmstudios.jungsoomarket.util.loadHttpsUrl

class ProductAdapter(private val clickListener: (Product) -> Unit): ListAdapter<Product,RecyclerView.ViewHolder>(ProductDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ProductViewHolder.from(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val product = getItem(position)
        when(holder){
            is ProductViewHolder -> {
                holder.bind(product)
                holder.binding.root.setOnClickListener {
                    clickListener(product)
                }
            }
        }
    }

    class ProductViewHolder(val binding: ProductItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(product: Product) {
            binding.apply {
                imgProduct.loadHttpsUrl(product.thumbnail)
                txtName.text = product.name
                txtPrice.text = product.price
            }
        }

        companion object {
            fun from(parent: ViewGroup) =
                ProductViewHolder(
                    ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                )
        }
    }

    object ProductDiffUtil: DiffUtil.ItemCallback<Product>(){
        override fun areItemsTheSame(oldItem: Product, newItem: Product) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Product, newItem: Product) =
            oldItem == newItem
    }
}