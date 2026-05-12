package com.stevenmarchy0013.simukmin.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.stevenmarchy0013.simukmin.model.Setoran
import kotlinx.coroutines.flow.Flow

@Dao
interface SetoranDao {

    @Insert
    suspend fun insert(setoran: Setoran)

    @Update
    suspend fun update(setoran: Setoran)

    @Delete
    suspend fun delete(setoran: Setoran)

    @Query("SELECT * FROM setoran ORDER BY id DESC")
    fun getSetoran(): Flow<List<Setoran>>

    @Query("SELECT * FROM setoran WHERE id = :id")
    suspend fun getSetoranById(id: Long): Setoran?

}