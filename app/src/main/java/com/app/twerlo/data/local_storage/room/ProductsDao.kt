package com.app.twerlo.data.local_storage.room

import androidx.room.*
import com.example.news.data.room.ProductsDatabaseEntity

@Dao
interface ProductsDao {

    @Query("SELECT * FROM productsdatabaseentity")
    suspend fun getAllTeams(): List<ProductsDatabaseEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(vararg news: ProductsDatabaseEntity)

}