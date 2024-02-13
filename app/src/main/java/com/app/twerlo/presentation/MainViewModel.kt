package com.app.twerlo.presentation

import androidx.lifecycle.ViewModel
import com.app.twerlo.data.local_storage.prefs.PrefStore
import com.app.twerlo.domain.common.DataState
import com.app.twerlo.domain.common.DataState.Idle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val prefStore: PrefStore) : ViewModel() {

 fun isuser()=prefStore.isUser()
}