package com.app.twerlo.data.network

import kotlin.jvm.Throws

data class ApiResult<T, E>(
  val value: T? = null,
  val error: E? = null,
)
