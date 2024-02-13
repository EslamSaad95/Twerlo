package com.app.twerlo.domain.common

data class ApiResult<T, E>(
  val value: T? = null,
  val error: E? = null
)