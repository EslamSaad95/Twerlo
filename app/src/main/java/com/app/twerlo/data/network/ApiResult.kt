package com.app.twerlo.data.network

data class ApiResult<T, E>(
  val value: T? = null,
  val error: E? = null,
)
