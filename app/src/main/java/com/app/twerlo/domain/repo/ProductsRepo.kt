package com.app.twerlo.domain.repo

import com.app.twerlo.data.network.dto.ApiResult
import com.app.twerlo.domain.common.ErrorState
import com.app.twerlo.domain.entity.ProductsEntity

interface ProductsRepo {
  suspend fun getProducts(): ApiResult<List<ProductsEntity>, ErrorState>

  suspend fun getProductDetails(id:Int): ApiResult<ProductsEntity, ErrorState>
}