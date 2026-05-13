package com.stevenmarchy0013.simukmin.util

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.stevenmarchy0013.simukmin.database.AppDatabase
import com.stevenmarchy0013.simukmin.screen.DetailViewModel
import com.stevenmarchy0013.simukmin.screen.MainViewModel
import com.stevenmarchy0013.simukmin.screen.RecycleBinViewModel

class ViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        val dao = AppDatabase
            .getInstance(context)
            .setoranDao()

        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(dao) as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(dao) as T
        } else if (modelClass.isAssignableFrom(RecycleBinViewModel::class.java)) {
            return RecycleBinViewModel(dao) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}