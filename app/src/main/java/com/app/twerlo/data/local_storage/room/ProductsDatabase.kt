package com.app.twerlo.data.local_storage.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.news.data.room.ProductsDatabaseEntity

@Database(entities = [ProductsDatabaseEntity::class], version = 1, exportSchema = false)
abstract class ProductsDatabase : RoomDatabase() {
    abstract fun productDao(): ProductsDao
}