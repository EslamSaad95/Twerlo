package com.app.twerlo.data.mapper

import com.app.twerlo.data.network.dto.ProductsDto
import com.app.twerlo.domain.entity.ProductsEntity
import com.app.twerlo.data.local_storage.room.ProductsDatabaseDto

fun List<ProductsDto>.productsEntity(): List<ProductsEntity> {
  return this.map {
    ProductsEntity(
      it.category, it.description, it.id, it.image, it.price, it.rating.productRatingEntity(), it.title
    )
  }
}

fun List<ProductsDatabaseDto>.productsListEntity(): List<ProductsEntity> {
  return this.map {
    ProductsEntity(
      it.category,
      it.description,
      it.id,
      it.image,
      it.price,
      ProductsEntity.Rating(it.ratingCount, it.rating),
      it
        .title
    )
  }
}

fun List<ProductsEntity>.productsDatabase(): List<ProductsDatabaseDto> {
  return this.map {
    ProductsDatabaseDto(it.id,it.title,it.price,it.description,it.category,it.image,it.rating.rate,it
      .rating.count)
  }
}

fun ProductsDto.Rating.productRatingEntity(): ProductsEntity.Rating {
  return ProductsEntity.Rating(this.count, this.rate)
}

fun ProductsDto.productDetailsEntity(): ProductsEntity {
  return ProductsEntity(
    this.category,
    this.description,
    this.id,
    this.image,
    this.price,
    this.rating.productRatingEntity(),
    this.title
  )
}

fun ProductsEntity.productDataBaseEntity(): ProductsDatabaseDto {
  return ProductsDatabaseDto(
    this.id,
    this.title,
    this.price,
    this.description,
    this.category,
    this.image,
    this.rating.rate,
    this.rating.count
  )
}