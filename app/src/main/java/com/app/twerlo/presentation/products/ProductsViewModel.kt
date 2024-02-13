package com.app.twerlo.presentation.products

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.twerlo.domain.common.DataState
import com.app.twerlo.domain.userCase.LoginUseCase
import com.app.twerlo.domain.userCase.ProductsUseCase
import com.app.twerlo.presentation.common.UiText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductsViewModel @Inject constructor(
  private val useCase: ProductsUseCase
):ViewModel() {
  private val _state = MutableStateFlow<DataState>(DataState.Idle)
  val state get() = _state



  fun getProducts() {
      viewModelScope.launch {
        state.value = DataState.Loading(fullScreen = false)
        useCase.getProducts().collect { loginUseCase ->
          loginUseCase.value?.let { products ->
            _state.value = DataState.Success(products)
          }
          loginUseCase.error?.let { errorState ->
            if(errorState.message.isNullOrEmpty().not())
              state.value = DataState.Error(UiText.DynamicString(errorState.message.toString()))
          }
        }

      }
  }

}