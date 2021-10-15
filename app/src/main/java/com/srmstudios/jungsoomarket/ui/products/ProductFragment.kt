package com.srmstudios.jungsoomarket.ui.products

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.srmstudios.jungsoomarket.R
import com.srmstudios.jungsoomarket.databinding.FragmentProductBinding
import com.srmstudios.jungsoomarket.ui.view_model.JungsooViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductFragment: Fragment(R.layout.fragment_product){
    private lateinit var binding: FragmentProductBinding
    private val viewModel: JungsooViewModel by viewModels()
    private lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProductBinding.bind(view)

        adapter = ProductAdapter { product ->
            findNavController().navigate(ProductFragmentDirections.actionHomeFragmentToProductDetailFragment(
                product.name,
                product
            ))
        }
        binding.recyclerviewProducts.adapter = adapter

        viewModel.products.observe(viewLifecycleOwner) { products ->
            adapter.submitList(products)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_product,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item,findNavController()) ||
                super.onOptionsItemSelected(item)
    }
}