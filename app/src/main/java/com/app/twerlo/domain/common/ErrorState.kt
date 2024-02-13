package com.app.twerlo.domain.common

data class ErrorState(
  val statusCode:Int=0,
  val message:String?="",
)
