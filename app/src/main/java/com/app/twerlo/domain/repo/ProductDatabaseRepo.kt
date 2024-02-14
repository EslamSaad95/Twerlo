package com.app.twerlo.domain.repo

import com.app.twerlo.domain.entity.ProductsEntity

interface ProductDatabaseRepo {


    suspend fun addProductToDB(productsEntity: ProductsEntity)


    suspend fun getAllProductsFromDB(): List<ProductsEntity>

}