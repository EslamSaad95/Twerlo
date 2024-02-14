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
      var failureType=FailureType.UnKnownError
      when (throwable) {
        is HttpException -> {
          failureType = when(throwable.code()) {
            400 -> FailureType.InvalidInput
            401 -> FailureType.UnAuthorizedAccess
            403 -> FailureType.Forbidden
            404 -> FailureType.NotFound
            500, 503 -> FailureType.ServerError
            else -> FailureType.UnKnownError
          }
        }
        is SocketTimeoutException -> failureType=FailureType.ConnectionError
        is IOException -> failureType=FailureType.ConnectionError
        is SocketException -> failureType=FailureType.ConnectionError
      }

      ApiResult(error =ErrorState(failureType=failureType))
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
      var failureType= FailureType.UnKnownError
      when (throwable) {
        is HttpException -> {
          failureType = when(throwable.code()) {
            400 -> FailureType.InvalidInput
            401 -> FailureType.UnAuthorizedAccess
            403 -> FailureType.Forbidden
            404 -> FailureType.NotFound
            500, 503 -> FailureType.ServerError
            else -> FailureType.UnKnownError
          }
        }
        is SocketTimeoutException -> failureType= FailureType.ConnectionError
        is IOException -> failureType= FailureType.ConnectionError
        is SocketException -> failureType= FailureType.ConnectionError
      }

      ApiResult(error =ErrorState(failureType=failureType))
    }
  }
}