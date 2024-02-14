package com.example.news.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductsDatabaseEntity(

  @PrimaryKey()
  @ColumnInfo(name = "id")
  val id: Int,

  @ColumnInfo(name = "title")
  val title: String,

  @ColumnInfo(name = "price")
  val price: Double,

  @ColumnInfo(name = "description")
  val description: String,

  @ColumnInfo(name = "category")
  val category: String,

  @ColumnInfo(name = "image")
  val image: String,

  @ColumnInfo(name = "rating")
  val rating: Double,

  @ColumnInfo(name = "ratingCount")
  val ratingCount: Int
)