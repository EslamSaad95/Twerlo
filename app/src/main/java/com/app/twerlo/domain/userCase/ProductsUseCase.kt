package com.app.twerlo.domain.userCase

import com.app.twerlo.domain.repo.LoginRepo
import com.app.twerlo.domain.repo.ProductDatabaseRepo
import com.app.twerlo.domain.repo.ProductsRepo
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class ProductsUseCase  @Inject constructor(private val repository: ProductsRepo,
  private val databaseRepo: ProductDatabaseRepo) {

  suspend fun getProducts() = flow  {
      emit(repository.getProducts())
  }

  suspend fun getProductDetails(id:Int) = flow {
    emit(repository.getProductDetails(id))
  }
}
