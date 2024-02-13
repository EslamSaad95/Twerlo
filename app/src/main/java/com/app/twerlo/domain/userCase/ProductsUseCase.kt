package com.app.twerlo.domain.userCase

import com.app.twerlo.domain.repo.LoginRepo
import com.app.twerlo.domain.repo.ProductsRepo
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductsUseCase  @Inject constructor(private val repository: ProductsRepo) {

  suspend fun getProducts() = flow {
    emit(repository.getProducts())
  }
}
