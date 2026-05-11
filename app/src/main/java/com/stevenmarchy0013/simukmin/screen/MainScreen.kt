package com.stevenmarchy0013.simukmin.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.stevenmarchy0013.simukmin.model.Setoran

@Composable
fun ScreenContent(modifier: Modifier = Modifier) {

    val viewModel: MainViewModel = viewModel()
    val data = viewModel.data

    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {

        items(data) {

            ListItem(setoran = it)

            HorizontalDivider()
        }
    }
}

@Composable
fun ListItem(setoran: Setoran) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),

        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Text(text = setoran.namaSiswa)

        Text(text = setoran.surah)

        Text(text = "Ayat ${setoran.ayat}")

        Text(text = setoran.tanggal)

        Text(text = setoran.catatan)
    }
}