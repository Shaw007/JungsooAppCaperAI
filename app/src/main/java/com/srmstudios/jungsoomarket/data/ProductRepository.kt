package com.srmstudios.jungsoomarket.data

import androidx.lifecycle.liveData
import com.srmstudios.jungsoomarket.data.database.JungsooDatabase
import com.srmstudios.jungsoomarket.data.database.entity.CartItem
import com.srmstudios.jungsoomarket.data.database.entity.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val jungsooDatabase: JungsooDatabase
) {
    val products = jungsooDatabase.productDao.getAllProducts()

    val cartItems = jungsooDatabase.cartItemDao.getAllCartItemsJoinProduct()

    suspend fun addProductInCart(product: Product){
        jungsooDatabase.cartItemDao.apply {
            val existingProductQuantity = getProductQuantity(product.id)
            if(existingProductQuantity == null){
                // this means that this Product is not present in the Cart
                insert(CartItem(productId = product.id,quantity = 1))
            } else{
                // this means this Product in present in the Cart
                // so just increase the quantity by 1
                updateQuantity(product.id,existingProductQuantity + 1)
            }
        }
    }

    suspend fun removeProductFromCart(cartItemId: Int){
        jungsooDatabase.cartItemDao.delete(cartItemId)
    }

    fun calculateTotalCartPrice() = flow {
        var totalPrice = 0.0
        try {
            jungsooDatabase.cartItemDao.getAllCartItemsJoinProduct().first().forEach { cartItem ->
                // omitting first character from the price string because it is a $
                totalPrice += cartItem.productPrice.substring(1,cartItem.productPrice.length-1).toDouble() * cartItem.productQuantity
            }
        }catch (ex: Exception){
            ex.printStackTrace()
            totalPrice = 0.0
        }finally {
            emit(totalPrice)
        }
    }
}