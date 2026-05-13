package com.stevenmarchy0013.simukmin.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "deleted_setoran")
data class DeletedSetoran(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val originalId: Long,
    val namaSiswa: String,
    val surah: String,
    val ayat: String,
    val catatan: String,
    val deletedAt: Long = System.currentTimeMillis()
)