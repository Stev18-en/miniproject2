package com.stevenmarchy0013.simukmin.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stevenmarchy0013.simukmin.database.SetoranDao
import com.stevenmarchy0013.simukmin.model.Setoran
import com.stevenmarchy0013.simukmin.model.DeletedSetoran
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
        catatan: String,
    ) {
        val setoran = Setoran(
            id = id,
            namaSiswa = namaSiswa,
            surah = surah,
            ayat = ayat,
            catatan = catatan,

        )
        viewModelScope.launch(Dispatchers.IO) {
            dao.update(setoran)
        }
    }
    suspend fun getSetoran(id: Long): Setoran? {

        return dao.getSetoranById(id)
    }

    fun deleteData(setoran: Setoran) {
        viewModelScope.launch(Dispatchers.IO) {

            val deletedSetoran = DeletedSetoran(
                originalId = setoran.id,
                namaSiswa = setoran.namaSiswa,
                surah = setoran.surah,
                ayat = setoran.ayat,
                catatan = setoran.catatan
            )

            dao.insertDeletedSetoran(deletedSetoran)
            dao.delete(setoran)
        }
    }
}