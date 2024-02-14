package com.app.twerlo.data.di

import android.content.Context
import com.app.twerlo.data.local_storage.room.ProductsDao
import com.app.twerlo.data.local_storage.room.ProductsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
  @Provides
  fun provideChannelDao(appDatabase: ProductsDatabase): ProductsDao {
    return appDatabase.productDao()
  }


  @Provides
  @Singleton
  fun provideAppDatabase(@ApplicationContext appContext: Context): ProductsDao {
    return Room.databaseBuilder(
      appContext,
      ProductsDatabase::class.java, "products"
    ).build()
  }
}