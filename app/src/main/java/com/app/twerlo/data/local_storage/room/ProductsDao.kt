package com.app.twerlo.data.local_storage.room

import androidx.room.*

@Dao
interface ProductsDao {

    @Query("SELECT * FROM productsTable")
    suspend fun getAllProducts(): List<ProductsDatabaseDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: ProductsDatabaseDto)


}