package com.app.twerlo.presentation.products

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.twerlo.data.local_storage.prefs.PrefStore
import com.app.twerlo.presentation.common.DataState
import com.app.twerlo.domain.common.FailureType
import com.app.twerlo.domain.userCase.ProductDatabaseUseCase
import com.app.twerlo.domain.userCase.ProductsUseCase
import com.app.twerlo.presentation.common.UiText
import com.app.twerlo.presentation.common.toUiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
  private val useCase: ProductsUseCase,
  private val dbUseCase:ProductDatabaseUseCase,
  private val prefStore: PrefStore
) : ViewModel() {

  private val _state = MutableStateFlow<DataState>(DataState.Idle)
  val state get() = _state




  private val _restartAppState = MutableStateFlow(false)
  val restartAppState get() = _restartAppState

  fun getProducts() {
    viewModelScope.launch {
      state.value = DataState.Loading(fullScreen = false)
      useCase.getProducts().collect { productUseCase ->
        productUseCase.value?.let { products ->
          _state.value = DataState.Success(products)
          products.forEach { productItem ->
            dbUseCase.insertProductEntityToDatabase(productItem)
          }
        }

        productUseCase.error?.let { errorState ->
          if (errorState.message.isNullOrEmpty().not())
            state.value = DataState.Error(errorState.toUiText())
          else if (errorState.failureType != null) {
            when (errorState.failureType) {
              FailureType.UnAuthorizedAccess -> {
                prefStore.clear()
                _restartAppState.value = true
              }

              FailureType.ConnectionError -> {
                dbUseCase.getProductsFromDatabase().collect { products ->
                  if (products.isEmpty())
                    state.value = DataState.Error(errorState.failureType.toUiText())
                  else
                    _state.value = DataState.Success(products)
                }
              }

              else -> state.value = DataState.Error(errorState.failureType.toUiText())
            }
          }
        }
      }

    }
  }
}