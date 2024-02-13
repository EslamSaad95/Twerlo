package com.app.twerlo.data.mapper

import com.app.twerlo.data.network.dto.ProductsDto
import com.app.twerlo.domain.entity.ProductsEntity

fun List<ProductsDto>.productsEntity(): List<ProductsEntity> {
  return this.map {
    ProductsEntity(
      it.category, it.description, it.id, it.image, it.price, it.rating.productRatingEntity(), it.title
    )
  }
}

fun ProductsDto.Rating.productRatingEntity(): ProductsEntity.Rating {
  return ProductsEntity.Rating(this.count, this.rate)
}