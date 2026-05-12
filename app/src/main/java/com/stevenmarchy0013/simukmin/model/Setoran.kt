package com.stevenmarchy0013.simukmin.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "setoran")
data class Setoran(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val namaSiswa: String,
    val surah: String,
    val ayat: String,
    val tanggal: String,
    val catatan: String
)
