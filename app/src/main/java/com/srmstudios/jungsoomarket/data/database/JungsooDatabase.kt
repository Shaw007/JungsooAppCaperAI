package com.srmstudios.jungsoomarket.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.srmstudios.jungsoomarket.data.database.dao.CartItemDao
import com.srmstudios.jungsoomarket.data.database.dao.ProductDao
import com.srmstudios.jungsoomarket.data.database.entity.CartItem
import com.srmstudios.jungsoomarket.data.database.entity.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Product::class,CartItem::class], version = 1)
abstract class JungsooDatabase: RoomDatabase() {

    abstract val productDao: ProductDao

    abstract val cartItemDao: CartItemDao

}

class JungsooDatabaseCallback @Inject constructor(
    private val jungsooDatabase: Provider<JungsooDatabase>,
    private val appCoroutineScope: CoroutineScope
): RoomDatabase.Callback(){

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)

        appCoroutineScope.launch {
            jungsooDatabase.get().productDao.insertAll(getDummyProducts())
        }
    }

    private fun getDummyProducts() =
        listOf(
            Product(
                "0001",
                "https://zxing.org/w/chart?cht=qr&chs=350x350&chld=L&choe=UTF-8&chl=0001",
                "https://emojipedia-us.s3.dualstack.us-west-1.amazonaws.com/thumbs/120/apple/237/banana_1f34c.png",
                "Banana",
                "\$1.00"
            ),
            Product(
                "0002",
                "https://zxing.org/w/chart?cht=qr&chs=350x350&chld=L&choe=UTF-8&chl=0002",
                "https://emojipedia-us.s3.dualstack.us-west-1.amazonaws.com/thumbs/120/apple/237/red-apple_1f34e.png",
                "Apple",
                "\$4.00"
            ),
            Product(
                "0003",
                "https://zxing.org/w/chart?cht=qr&chs=350x350&chld=L&choe=UTF-8&chl=0003",
                "https://emojipedia-us.s3.dualstack.us-west-1.amazonaws.com/thumbs/120/apple/237/sparkles_2728.png",
                "Other Stuff",
                "\$10.00"
            )
        )
}







