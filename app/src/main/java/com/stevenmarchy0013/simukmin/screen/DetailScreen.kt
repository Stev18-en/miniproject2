package com.stevenmarchy0013.simukmin.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.tooling.preview.Preview
import com.stevenmarchy0013.simukmin.R
import com.stevenmarchy0013.simukmin.ui.theme.SiMukminTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.detail_title),
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF2E7D32)
                )
            )
        }

    ) { innerPadding ->
        FormSetoran(
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun FormSetoran(modifier: Modifier = Modifier) {

    val namaSiswa = remember {
        mutableStateOf("")
    }
    val surah = remember {
        mutableStateOf("")
    }
    val ayat = remember {
        mutableStateOf("")
    }
    val catatan = remember {
        mutableStateOf("")
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),

        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = namaSiswa.value,
            onValueChange = {
                namaSiswa.value = it
            },
            label = {
                Text(text = stringResource(R.string.input_nama))
            },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = surah.value,
            onValueChange = {
                surah.value = it
            },
            label = {
                Text(text = stringResource(R.string.input_surah))
            },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = ayat.value,
            onValueChange = {
                ayat.value = it
            },
            label = {
                Text(text = stringResource(R.string.input_ayat))
            },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = catatan.value,
            onValueChange = {
                catatan.value = it
            },
            label = {
                Text(text = stringResource(R.string.input_catatan))
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}
@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    SiMukminTheme {
        DetailScreen(
            navController = rememberNavController()
        )
    }
}