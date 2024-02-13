package com.app.twerlo.data.util

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

/**
 * Ahmed Elmokadim
 * elmokadim@gmail.com
 * 15/07/2023
 */
sealed class UiText {

  data object Empty : UiText()

  data class DynamicString(val value: String) : UiText()

  class StringResource(
    @StringRes val resId: Int,
    vararg val args: Any
  ) : UiText()

  @Composable
  fun asString() = when (this) {
    is Empty -> ""
    is DynamicString -> value
    is StringResource -> stringResource(resId, args)
  }

  fun asString(context: Context) = when (this) {
    is Empty -> ""
    is DynamicString -> value
    is StringResource -> context.getString(resId, args)
  }

  fun isEmpty() = when (this) {
    is Empty -> true
    else -> false
  }

  fun isNotEmpty() = when (this) {
    is Empty -> false
    else -> true
  }
}