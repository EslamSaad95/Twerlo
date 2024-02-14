package com.app.twerlo.domain.userCase

import com.app.twerlo.data.network.ApiResult
import com.app.twerlo.domain.repo.LoginRepo
import com.app.twerlo.domain.repo.ProductDatabaseRepo
import com.app.twerlo.domain.repo.ProductsRepo
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class ProductsUseCase @Inject constructor(
  private val repository: ProductsRepo, private val databaseRepo: ProductDatabaseRepo
) {

  suspend fun getProducts() = flow {
    if (repository.getProducts().value == null)
      emit(ApiResult(databaseRepo.getAllProductsFromDB(),repository.getProducts().error))
    else {
      repository.getProducts().value?.let {
        it.forEach {
          databaseRepo.addProductToDB(it)
        }
      }
      emit(repository.getProducts())
    }
  }

  suspend fun getProductDetails(id: Int) = flow {
    if (repository.getProducts().value == null) emit(
      ApiResult(
      databaseRepo.getAllProductsFromDB().filter { it.id == id },repository.getProductDetails(id).error))
    else emit(repository.getProductDetails(id))
  }
}
