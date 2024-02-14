package com.app.twerlo.presentation.productDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.twerlo.data.local_storage.prefs.PrefStore
import com.app.twerlo.domain.common.DataState
import com.app.twerlo.domain.common.FailureType
import com.app.twerlo.domain.userCase.ProductsUseCase
import com.app.twerlo.presentation.common.UiText
import com.app.twerlo.presentation.common.toUiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
  private val useCase: ProductsUseCase,
  private val prefStore: PrefStore
) : ViewModel() {

  private val _state = MutableStateFlow<DataState>(DataState.Idle)
  val state get() = _state

  private val _restartAppState = MutableStateFlow(false)
  val restartAppState get() = _restartAppState

  fun getProductDetails(id: Int) {
    viewModelScope.launch {
      state.value = DataState.Loading(fullScreen = false)
      useCase.getProductDetails(id).collect { loginUseCase ->
        loginUseCase.value?.let { products ->
          _state.value = DataState.Success(products)
        }
        loginUseCase.error?.let { errorState ->
          if (errorState.message.isNullOrEmpty().not())
            state.value = DataState.Error(UiText.DynamicString(errorState.message.toString()))
          else if(errorState.failureType!=null)
          {
            if (errorState.failureType == FailureType.UnAuthorizedAccess) {
              prefStore.clear()
              _restartAppState.value = true
            } else
              state.value = DataState.Error(errorState.failureType.toUiText())
          }
        }
      }

    }
  }
}