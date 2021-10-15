package com.srmstudios.jungsoomarket.ui.cart

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.srmstudios.jungsoomarket.R
import com.srmstudios.jungsoomarket.databinding.FragmentCartBinding
import com.srmstudios.jungsoomarket.ui.view_model.JungsooViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment: Fragment(R.layout.fragment_cart){
    private lateinit var binding: FragmentCartBinding
    private val viewModel: JungsooViewModel by activityViewModels()
    private lateinit var adapter: CartAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCartBinding.bind(view)

        adapter = CartAdapter { cartItem ->
            showRemoveProductDialog(cartItem.cartItemId)
        }
        binding.recyclerviewCart.adapter = adapter

        viewModel.cartItems.observe(viewLifecycleOwner) { cartItems ->
            adapter.submitList(cartItems)
        }

        viewModel.totalCartPrice.observe(viewLifecycleOwner) { totalCartPrice ->
            binding.txtTotal.text = "${getString(R.string.total_with_colon)} $$totalCartPrice"
        }
    }

    private fun showRemoveProductDialog(cartItemId: Int) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.remove_product))
            .setMessage(getString(R.string.remove_product_confirmation))
            .setCancelable(false)
            .setNegativeButton(getString(R.string.no)) { _, _ ->

            }
            .setPositiveButton(getString(R.string.yes)) { _, _ ->
                viewModel.removeProductFromCart(cartItemId)
            }
            .show()
    }
}




