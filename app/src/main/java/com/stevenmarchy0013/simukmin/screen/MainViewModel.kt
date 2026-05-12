package com.stevenmarchy0013.simukmin.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stevenmarchy0013.simukmin.database.SetoranDao
import com.stevenmarchy0013.simukmin.model.Setoran
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class MainViewModel(dao: SetoranDao) : ViewModel() {

    val data: StateFlow<List<Setoran>> =
        dao.getSetoran().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList()
        )

    fun getSetoran(id: Long): Setoran? {

        return data.value.find {

            it.id == id
        }
    }
}