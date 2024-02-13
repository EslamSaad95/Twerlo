package com.app.twerlo.data.di

import com.app.twerlo.data.local_storage.prefs.PrefStore
import com.app.twerlo.data.local_storage.prefs.PrefStoreImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PreferencesModule {

  @Binds
  @Singleton
  abstract fun providePrefStore(prefStoreImpl: PrefStoreImpl): PrefStore
}gi