package com.app.twerlo.domain.userCase

import com.app.twerlo.domain.entity.ProductsEntity
import com.app.twerlo.domain.repo.ProductDatabaseRepo
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductDatabaseUseCase @Inject constructor(private val databaseRepo: ProductDatabaseRepo) {
  suspend fun getProductsFromDatabase() = flow {
    emit(databaseRepo.getAllProductsFromDB())
  }
  suspend fun insertProductEntityToDatabase(productsEntity: ProductsEntity) {
    databaseRepo.addProductToDB(productsEntity)
  }
  suspend fun getProductFromDatabase(id: Int) = flow {
    val filteredProductItem=databaseRepo.getAllProductsFromDB().filter { it.id==id }
    emit(if(filteredProductItem.isEmpty().not())filteredProductItem[0]else null)
  }
}