package com.stevenmarchy0013.simukmin.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import com.stevenmarchy0013.simukmin.model.Setoran
import com.stevenmarchy0013.simukmin.model.DeletedSetoran

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

    @Insert
    suspend fun insertDeletedSetoran(deletedSetoran: DeletedSetoran)

    @Query("SELECT * FROM deleted_setoran ORDER BY deletedAt DESC")
    fun getDeletedSetoran(): Flow<List<DeletedSetoran>>

    @Delete
    suspend fun deleteDeletedSetoran(deletedSetoran: DeletedSetoran)

    @Query("DELETE FROM deleted_setoran")
    suspend fun clearDeletedSetoran()

}