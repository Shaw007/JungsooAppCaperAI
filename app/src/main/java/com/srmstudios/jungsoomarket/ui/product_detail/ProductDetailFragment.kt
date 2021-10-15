package com.srmstudios.jungsoomarket.ui.product_detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.srmstudios.jungsoomarket.R
import com.srmstudios.jungsoomarket.databinding.FragmentProductDetailBinding
import com.srmstudios.jungsoomarket.ui.view_model.JungsooViewModel
import com.srmstudios.jungsoomarket.util.loadHttpsUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailFragment: Fragment(R.layout.fragment_product_detail){
    private lateinit var binding: FragmentProductDetailBinding
    private val viewModel: JungsooViewModel by activityViewModels()
    private val args: ProductDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProductDetailBinding.bind(view)

        binding.apply {
            imgQrCode.loadHttpsUrl(args.product.qrUrl)
            imgQrCode.setOnClickListener {
                viewModel.addProductToCart(args.product)
                Toast.makeText(context,getString(R.string.product_added_successfully),Toast.LENGTH_SHORT).show()
            }
        }
    }
}








