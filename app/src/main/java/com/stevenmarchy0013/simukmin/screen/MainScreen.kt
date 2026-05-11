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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.res.stringResource
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.clickable
import androidx.compose.ui.tooling.preview.Preview
import com.stevenmarchy0013.simukmin.ui.theme.SiMukminTheme
import com.stevenmarchy0013.simukmin.model.Setoran
import com.stevenmarchy0013.simukmin.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.app_name),
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF2E7D32)
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {

                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.add_setoran)
                )
            }
        }

    ) { innerPadding ->

        ScreenContent(
            modifier = Modifier.padding(innerPadding)
        )
    }
}
@Composable
fun ScreenContent(modifier: Modifier = Modifier) {

    val viewModel: MainViewModel = viewModel()
    val data = viewModel.data

    if (data.isEmpty()) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.empty_list)
            )
        }
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize()
        ) {
            items(data) {
                ListItem(setoran = it,onClick = { })
                HorizontalDivider()
            }
        }
    }
}

@Composable
fun ListItem(setoran: Setoran, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = setoran.namaSiswa,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = stringResource(R.string.label_surah,setoran.surah),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = stringResource(R.string.label_ayat,setoran.ayat),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = setoran.catatan,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = setoran.tanggal,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    SiMukminTheme {
        MainScreen()
    }
}