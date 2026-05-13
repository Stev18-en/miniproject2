package com.stevenmarchy0013.simukmin.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stevenmarchy0013.simukmin.database.SetoranDao
import com.stevenmarchy0013.simukmin.model.DeletedSetoran
import com.stevenmarchy0013.simukmin.model.Setoran
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecycleBinViewModel(
    private val dao: SetoranDao
) : ViewModel() {

    val data = dao.getDeletedSetoran()

    fun restoreData(deletedSetoran: DeletedSetoran) {
        viewModelScope.launch(Dispatchers.IO) {
            val restoredSetoran = Setoran(
                namaSiswa = deletedSetoran.namaSiswa,
                surah = deletedSetoran.surah,
                ayat = deletedSetoran.ayat,
                catatan = deletedSetoran.catatan
            )

            dao.insert(restoredSetoran)
            dao.deleteDeletedSetoran(deletedSetoran)
        }
    }

    fun deletePermanent(deletedSetoran: DeletedSetoran) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteDeletedSetoran(deletedSetoran)
        }
    }

    fun clearRecycleBin() {
        viewModelScope.launch(Dispatchers.IO) {
            dao.clearDeletedSetoran()
        }
    }
}