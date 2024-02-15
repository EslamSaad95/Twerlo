package com.app.twerlo.data.repo

import com.app.twerlo.data.local_storage.room.ProductsDao
import com.app.twerlo.data.mapper.productDataBaseEntity
import com.app.twerlo.data.mapper.productsListEntity
import com.app.twerlo.domain.entity.ProductsEntity
import com.app.twerlo.domain.repo.ProductDatabaseRepo
import javax.inject.Inject

class DatabaseRepositoryImpl @Inject constructor(private val productDao: ProductsDao) : ProductDatabaseRepo {

  override suspend fun addProductToDB(productsEntity: ProductsEntity) {
    productDao.insertProduct(productsEntity.productDataBaseEntity())
  }

  override suspend fun getAllProductsFromDB(): List<ProductsEntity> {
   return  productDao.getAllProducts().productsListEntity()
  }
}