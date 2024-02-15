package com.app.twerlo.data.repo

import com.app.twerlo.data.mapper.toLoginEntity
import com.app.twerlo.data.network.dto.ApiResult
import com.app.twerlo.data.network.ApiService
import com.app.twerlo.domain.common.ErrorState
import com.app.twerlo.domain.entity.LoginEntity
import com.app.twerlo.domain.repo.LoginRepo
import retrofit2.HttpException
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
      ApiResult(error =ErrorState(failureType=throwable.mapToFailureType()))
    }
  }
}
