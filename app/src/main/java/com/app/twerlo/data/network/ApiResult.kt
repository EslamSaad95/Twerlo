package com.app.twerlo.data.network

/**
 * Ahmed Elmokadim
 * elmokadim@gmail.com
 * 15/07/2023
 */
data class ApiResult<T, E>(
  val value: T? = null,
  val error: E? = null,
  val statusCode: Int = 0
)
