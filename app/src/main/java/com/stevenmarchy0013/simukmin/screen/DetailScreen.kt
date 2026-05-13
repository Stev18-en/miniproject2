package com.stevenmarchy0013.simukmin.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.stevenmarchy0013.simukmin.R
import com.stevenmarchy0013.simukmin.model.Setoran
import com.stevenmarchy0013.simukmin.ui.theme.SiMukminTheme
import com.stevenmarchy0013.simukmin.util.ViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navController: NavHostController,
    id: Long
) {
    val context = LocalContext.current
    val factory = ViewModelFactory(context)
    val viewModel: DetailViewModel = viewModel(factory = factory)

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

    val showDialog = remember {
        mutableStateOf(false)
    }

    LaunchedEffect(id) {
        if (id != -1L) {
            val setoran = viewModel.getSetoran(id)

            setoran?.let {
                namaSiswa.value = it.namaSiswa
                surah.value = it.surah
                ayat.value = it.ayat
                catatan.value = it.catatan
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.detail_title)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->

        FormSetoran(
            namaSiswa = namaSiswa.value,
            onNamaSiswaChange = {
                namaSiswa.value = it
            },

            surah = surah.value,
            onSurahChange = {
                surah.value = it
            },

            ayat = ayat.value,
            onAyatChange = {
                ayat.value = it
            },

            catatan = catatan.value,
            onCatatanChange = {
                catatan.value = it
            },

            onSimpan = {
                if (
                    namaSiswa.value == "" ||
                    surah.value == "" ||
                    ayat.value == ""
                ) {
                    Toast.makeText(
                        context,
                        R.string.invalid,
                        Toast.LENGTH_LONG
                    ).show()

                    return@FormSetoran
                }

                if (id == -1L) {
                    viewModel.insert(
                        namaSiswa = namaSiswa.value,
                        surah = surah.value,
                        ayat = ayat.value,
                        catatan = catatan.value
                    )
                } else {
                    viewModel.update(
                        id = id,
                        namaSiswa = namaSiswa.value,
                        surah = surah.value,
                        ayat = ayat.value,
                        catatan = catatan.value
                    )
                }

                navController.popBackStack()
            },

            onHapus = {
                showDialog.value = true
            },

            isEdit = id != -1L,

            modifier = Modifier.padding(innerPadding)
        )

        if (id != -1L && showDialog.value) {
            DisplayAlertDialog(
                onDismissRequest = {
                    showDialog.value = false
                },

                onConfirmation = {
                    showDialog.value = false

                    val setoran = Setoran(
                        id = id,
                        namaSiswa = namaSiswa.value,
                        surah = surah.value,
                        ayat = ayat.value,
                        catatan = catatan.value
                    )

                    viewModel.deleteData(setoran)
                    navController.popBackStack()
                }
            )
        }
    }
}

@Composable
fun FormSetoran(
    namaSiswa: String,
    onNamaSiswaChange: (String) -> Unit,

    surah: String,
    onSurahChange: (String) -> Unit,

    ayat: String,
    onAyatChange: (String) -> Unit,

    catatan: String,
    onCatatanChange: (String) -> Unit,

    onSimpan: () -> Unit,

    onHapus: () -> Unit,

    isEdit: Boolean,

    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),

        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = namaSiswa,
            onValueChange = onNamaSiswaChange,

            label = {
                Text(text = stringResource(R.string.input_nama))
            },

            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = surah,
            onValueChange = onSurahChange,

            label = {
                Text(text = stringResource(R.string.input_surah))
            },

            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = ayat,
            onValueChange = onAyatChange,

            label = {
                Text(text = stringResource(R.string.input_ayat))
            },

            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = catatan,
            onValueChange = onCatatanChange,

            label = {
                Text(text = stringResource(R.string.input_catatan))
            },

            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = onSimpan,

            modifier = Modifier.fillMaxWidth(),

            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text(
                text = stringResource(R.string.btn_simpan)
            )
        }

        if (isEdit) {
            Button(
                onClick = onHapus,

                modifier = Modifier.fillMaxWidth(),

                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error,
                    contentColor = MaterialTheme.colorScheme.onError
                )
            ) {
                Text(
                    text = stringResource(R.string.btn_hapus)
                )
            }
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