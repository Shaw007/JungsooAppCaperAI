package com.srmstudios.jungsoomarket.ui.view_model

import androidx.lifecycle.*
import com.srmstudios.jungsoomarket.data.ProductRepository
import com.srmstudios.jungsoomarket.data.database.entity.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JungsooViewModel @Inject constructor(
    private val productRepository: ProductRepository
): ViewModel() {

    val products = productRepository.products.asLiveData()

    val cartItems = productRepository.cartItems.asLiveData()

    val totalCartPrice = cartItems.switchMap {
        productRepository.calculateTotalCartPrice().asLiveData()
    }

    fun addProductToCart(product: Product) = viewModelScope.launch(Dispatchers.IO) {
        productRepository.addProductInCart(product)
    }

    fun removeProductFromCart(cartItemId: Int) = viewModelScope.launch(Dispatchers.IO) {
        productRepository.removeProductFromCart(cartItemId)
    }
}