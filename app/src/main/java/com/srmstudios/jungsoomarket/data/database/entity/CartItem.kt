package com.srmstudios.jungsoomarket.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CartItem(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "product_id") val productId: String,
    val quantity: Int
)

data class CartItemJoinProduct(
    @ColumnInfo(name = "cart_item_id") val cartItemId: Int,
    @ColumnInfo(name = "product_id") val productId: String,
    @ColumnInfo(name = "product_name") val productName: String,
    @ColumnInfo(name = "product_price") val productPrice: String,
    @ColumnInfo(name = "product_thumbnail") val productThumbnail: String,
    @ColumnInfo(name = "product_quantity") val productQuantity: Int
)