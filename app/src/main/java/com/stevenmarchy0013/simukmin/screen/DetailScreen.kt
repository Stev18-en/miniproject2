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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.stevenmarchy0013.simukmin.R
import com.stevenmarchy0013.simukmin.model.Setoran
import com.stevenmarchy0013.simukmin.ui.theme.SiMukminTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavHostController, id: Long) {
    val viewModel: MainViewModel = viewModel()
    val data = viewModel.getSetoran(id)

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
            data = data,
            onSimpan = {
                navController.popBackStack()
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun FormSetoran(data: Setoran?, onSimpan:()-> Unit, modifier: Modifier = Modifier) {

    val namaSiswa = remember {
        mutableStateOf(data?.namaSiswa ?:"")
    }
    val surah = remember {
        mutableStateOf(data?.surah ?:"")
    }
    val ayat = remember {
        mutableStateOf(data?.ayat ?:"")
    }
    val catatan = remember {
        mutableStateOf(data?.catatan ?:"")
    }
    val namaError = remember {
        mutableStateOf(false)
    }
    val surahError = remember {
        mutableStateOf(false)
    }
    val ayatError = remember {
        mutableStateOf(false)
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
            isError = namaError.value,

            label = {
                Text(text = stringResource(R.string.input_nama))
            },
            modifier = Modifier.fillMaxWidth()
        )
        if (namaError.value) {

            Text(
                text = stringResource(R.string.error_nama),
                color = Color.Red,
                style = MaterialTheme.typography.bodySmall
            )
        }
        OutlinedTextField(
            value = surah.value,
            onValueChange = {
                surah.value = it
            },
            isError = surahError.value,
            label = {
                Text(text = stringResource(R.string.input_surah))
            },
            modifier = Modifier.fillMaxWidth()
        )
        if (surahError.value) {
            Text(
                text = stringResource(R.string.error_surah),
                color = Color.Red,
                style = MaterialTheme.typography.bodySmall
            )
        }
        OutlinedTextField(
            value = ayat.value,
            onValueChange = {
                ayat.value = it
            },
            isError = ayatError.value,
            label = {
                Text(text = stringResource(R.string.input_ayat))
            },
            modifier = Modifier.fillMaxWidth()
        )
        if (ayatError.value) {
            Text(
                text = stringResource(R.string.error_ayat),
                color = Color.Red,
                style = MaterialTheme.typography.bodySmall
            )
        }
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
        Button(
            onClick = {
                namaError.value = namaSiswa.value.isBlank()
                surahError.value = surah.value.isBlank()
                ayatError.value = ayat.value.isBlank()
                if (
                    !namaError.value &&
                    !surahError.value &&
                    !ayatError.value
                ) {
                    onSimpan()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2E7D32)
            )
        ) {
            Text(
                text = stringResource(R.string.btn_simpan)
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    SiMukminTheme {
        DetailScreen(
            navController = rememberNavController(),
            id = -1
        )
    }
}