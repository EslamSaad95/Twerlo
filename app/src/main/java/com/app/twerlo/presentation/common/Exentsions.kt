package com.app.twerlo.presentation.common

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper

inline fun <reified T : Any> Any.cast() = this as T

fun Context.findActivity(): Activity {
  var context = this
  while (context is ContextWrapper) {
    if (context is Activity) return context
    context = context.baseContext
  }
  throw IllegalStateException("no activity")
}