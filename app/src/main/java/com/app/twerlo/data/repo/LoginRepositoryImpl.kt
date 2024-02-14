package com.app.twerlo.data.repo

import com.app.twerlo.data.mapper.toLoginEntity
import com.app.twerlo.data.network.ApiResult
import com.app.twerlo.data.network.ApiService
import com.app.twerlo.domain.common.ErrorState
import com.app.twerlo.domain.common.FailureType
import com.app.twerlo.domain.entity.LoginEntity
import com.app.twerlo.domain.repo.LoginRepo
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketException
import java.net.SocketTimeoutException
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val apiService: ApiService):LoginRepo {

  override suspend fun login(userName: String, password: String): ApiResult<LoginEntity, ErrorState> {
    return try {
      val response = apiService.login(userName, password)
      if (response.isSuccessful)
        ApiResult(value = response.body()?.toLoginEntity())
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
}
