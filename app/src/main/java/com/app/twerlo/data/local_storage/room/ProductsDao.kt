package com.app.twerlo.data.local_storage.room

import androidx.room.*

@Dao
interface ProductsDao {

    @Query("SELECT * FROM newsdatabaseentity")
    suspend fun getAllTeams(): List<NewsDatabaseEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeam(vararg news: NewsDatabaseEntity)

    @Delete
    suspend fun delete(news: NewsDatabaseEntity)
}