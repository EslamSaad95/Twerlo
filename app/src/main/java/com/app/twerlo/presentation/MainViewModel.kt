package com.app.twerlo.presentation

import androidx.lifecycle.ViewModel
import com.app.twerlo.data.network.DataState
import com.app.twerlo.data.network.DataState.Idle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel() : ViewModel() {

  private val _state by lazy { MutableStateFlow<DataState>(Idle) }
  val state get() = _state.asStateFlow()
}