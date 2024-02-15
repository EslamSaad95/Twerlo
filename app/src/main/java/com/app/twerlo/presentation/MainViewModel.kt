package com.app.twerlo.presentation

import androidx.lifecycle.ViewModel
import com.app.twerlo.data.local_storage.prefs.PrefStore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val prefStore: PrefStore) : ViewModel() {

 fun isuser()=prefStore.isUser()
}