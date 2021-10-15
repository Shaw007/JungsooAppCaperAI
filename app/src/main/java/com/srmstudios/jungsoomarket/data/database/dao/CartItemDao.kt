package com.srmstudios.jungsoomarket.data.database.dao

import androidx.room.*
import com.srmstudios.jungsoomarket.data.database.entity.CartItem
import com.srmstudios.jungsoomarket.data.database.entity.CartItemJoinProduct
import com.srmstudios.jungsoomarket.data.database.entity.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface CartItemDao {

    @Insert
    suspend fun insert(cartItem: CartItem)

    @Query("update cartitem set quantity = :quantity where product_id=:productId")
    suspend fun updateQuantity(productId: String, quantity: Int)

    @Query("delete from cartitem where id =:id")
    suspend fun delete(id: Int)

    @Query("select ci.id as cart_item_id,p.id as product_id,p.name as product_name,p.price as product_price,p.thumbnail as product_thumbnail,ci.quantity as product_quantity from product p,cartitem ci where p.id = ci.product_id")
    fun getAllCartItemsJoinProduct(): Flow<List<CartItemJoinProduct>>

    @Query("select quantity from cartitem where product_id = :productId")
    fun getProductQuantity(productId: String): Int?
}