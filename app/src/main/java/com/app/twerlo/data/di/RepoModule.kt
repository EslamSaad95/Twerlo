package com.app.twerlo.data.di

import com.app.twerlo.data.repo.DatabaseRepositoryImpl
import com.app.twerlo.data.repo.LoginRepositoryImpl
import com.app.twerlo.data.repo.ProductsRepositoryImpl
import com.app.twerlo.domain.repo.LoginRepo
import com.app.twerlo.domain.repo.ProductDatabaseRepo
import com.app.twerlo.domain.repo.ProductsRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {

  @Singleton
  @Binds
  abstract fun provideLoginRepo(repo: LoginRepositoryImpl): LoginRepo

  @Singleton
  @Binds
  abstract fun provideProductsRepo(repo: ProductsRepositoryImpl): ProductsRepo

  @Singleton
  @Binds
  abstract fun provideDBRepo(repo: DatabaseRepositoryImpl): ProductDatabaseRepo
}