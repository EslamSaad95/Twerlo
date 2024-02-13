package com.app.twerlo.presentation

import androidx.lifecycle.ViewModel
import com.app.twerlo.domain.entity.DataState
import com.app.twerlo.domain.entity.DataState.Idle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel() : ViewModel() {

  private val _state by lazy { MutableStateFlow<DataState>(Idle) }
  val state get() = _state.asStateFlow()
}