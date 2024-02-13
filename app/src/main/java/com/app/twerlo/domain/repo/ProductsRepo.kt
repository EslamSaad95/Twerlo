package com.app.twerlo.domain.repo

import com.app.twerlo.data.network.ApiResult
import com.app.twerlo.domain.common.ErrorState
import com.app.twerlo.domain.entity.LoginEntity
import com.app.twerlo.domain.entity.ProductsEntity

interface ProductsRepo {
  suspend fun getProducts(): ApiResult<List<ProductsEntity>, ErrorState>
}