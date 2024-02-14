package com.app.twerlo.data.repo

import com.app.twerlo.data.mapper.productDetailsEntity
import com.app.twerlo.data.mapper.productsEntity
import com.app.twerlo.data.mapper.toLoginEntity
import com.app.twerlo.data.network.ApiResult
import com.app.twerlo.data.network.ApiService
import com.app.twerlo.domain.common.ErrorState
import com.app.twerlo.domain.common.FailureType
import com.app.twerlo.domain.entity.ProductsEntity
import com.app.twerlo.domain.repo.LoginRepo
import com.app.twerlo.domain.repo.ProductsRepo
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketException
import java.net.SocketTimeoutException
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(private val apiService: ApiService) : ProductsRepo {

  override suspend fun getProducts(): ApiResult<List<ProductsEntity>, ErrorState> {
    return try {
      val response = apiService.getProducts()
      if (response.isSuccessful)
        ApiResult(value = response.body()?.productsEntity())
      else {
        val error = response.errorBody()?.charStream()?.readText()
        error?.let {
          return ApiResult(error = ErrorState(response.code(), error.toString()))
        } ?: throw HttpException(response)
      }
    } catch (throwable: Throwable) {

      ApiResult(error = ErrorState(failureType = throwable.mapToFailureType()))
    }
  }

  override suspend fun getProductDetails(id: Int): ApiResult<ProductsEntity, ErrorState> {
    return try {
      val response = apiService.getProductDetails(id)
      if (response.isSuccessful)
        ApiResult(value = response.body()?.productDetailsEntity())
      else {
        val error = response.errorBody()?.charStream()?.readText()

        error?.let {
          return ApiResult(error = ErrorState(response.code(), error.toString()))
        } ?: throw HttpException(response)
      }
    } catch (throwable: Throwable) {
      ApiResult(error = ErrorState(failureType = throwable.mapToFailureType()))
    }
  }
}