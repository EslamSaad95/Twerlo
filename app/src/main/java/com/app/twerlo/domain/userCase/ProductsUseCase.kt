package com.app.twerlo.domain.userCase

import com.app.twerlo.domain.repo.ProductsRepo
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductsUseCase @Inject constructor(
  private val repository: ProductsRepo
) {

  suspend fun getProducts() = flow {
    emit(repository.getProducts())
  }

  suspend fun getProductDetails(id: Int) = flow {
    emit(repository.getProductDetails(id))
  }


}
