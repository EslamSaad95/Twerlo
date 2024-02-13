package com.app.twerlo.data.network

import com.app.twerlo.data.util.UiText

sealed class DataState() {

  data object Idle : DataState()

  data class Loading(val fullScreen: Boolean) : DataState()

  data class Success<out T>(val result: T) : DataState()

  data class Error(val error: UiText, val statusCode: Int) : DataState()
}