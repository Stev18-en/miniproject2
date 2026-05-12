package com.stevenmarchy0013.simukmin.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stevenmarchy0013.simukmin.database.SetoranDao
import com.stevenmarchy0013.simukmin.model.Setoran
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DetailViewModel(
    private val dao: SetoranDao
) : ViewModel() {

    private val formatter = SimpleDateFormat(
        "yyyy-MM-dd HH:mm:ss",
        Locale.US
    )
    fun insert(
        namaSiswa: String,
        surah: String,
        ayat: String,
        catatan: String
    ) {
        val setoran = Setoran(
            tanggal = formatter.format(Date()),
            namaSiswa = namaSiswa,
            surah = surah,
            ayat = ayat,
            catatan = catatan
        )
        viewModelScope.launch(Dispatchers.IO) {

            dao.insert(setoran)
        }
    }

    fun update(
        id: Long,
        namaSiswa: String,
        surah: String,
        ayat: String,
        catatan: String
    ) {
        val setoran = Setoran(
            id = id,
            tanggal = formatter.format(Date()),
            namaSiswa = namaSiswa,
            surah = surah,
            ayat = ayat,
            catatan = catatan
        )
        viewModelScope.launch(Dispatchers.IO) {
            dao.update(setoran)
        }
    }
    suspend fun getSetoran(id: Long): Setoran? {

        return dao.getSetoranById(id)
    }

    fun delete(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.getSetoranById(id)?.let {
                dao.delete(it)
            }
        }
    }
}