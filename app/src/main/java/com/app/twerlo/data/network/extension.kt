package com.app.twerlo.data.network

import com.app.twerlo.data.util.UiText
import com.google.gson.Gson
import retrofit2.HttpException
import retrofit2.Response

/**
 * Ahmed Elmokadim
 * elmokadim@gmail.com
 * 15/07/2023
 */
suspend fun <T> getResult(request: suspend () -> Response<T>): ApiResult<T, UiText> = try {
  val response = request.invoke()

  if (response.isSuccessful)
    ApiResult(value = response.body(), statusCode = response.code())
  else {
    val error = Gson().fromJson(response.errorBody()!!.charStream(), BaseRsm::class.java)
    error?.let { ApiResult(error = UiText.DynamicString(it.message), statusCode = response.code()) }
      ?: throw HttpException(response)
  }
} catch (throwable: Throwable) {
  ApiResult(error = throwable.map().message)
}