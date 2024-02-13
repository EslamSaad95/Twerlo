package com.app.twerlo.data.repo

import com.app.twerlo.data.mapper.toLoginEntity
import com.app.twerlo.data.network.ApiResult
import com.app.twerlo.data.network.ApiService
import com.app.twerlo.domain.entity.LoginEntity
import com.app.twerlo.domain.repo.LoginRepo
import retrofit2.HttpException
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val apiService: ApiService):LoginRepo {

  override suspend fun login(userName: String, password: String): ApiResult<LoginEntity, Throwable> {
    return try {
      val response = apiService.login(userName, password)
      if (response.isSuccessful)
        ApiResult(value = response.body()?.toLoginEntity())
      else

        throw HttpException(response)
    } catch (throwable: Throwable) {
      ApiResult(error = throwable)
    }
  }
}
