package com.stevenmarchy0013.simukmin.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.ViewList
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.stevenmarchy0013.simukmin.R
import com.stevenmarchy0013.simukmin.model.Setoran
import com.stevenmarchy0013.simukmin.navigation.Screen
import com.stevenmarchy0013.simukmin.ui.theme.SiMukminTheme
import com.stevenmarchy0013.simukmin.util.SettingsDataStore
import com.stevenmarchy0013.simukmin.util.ViewModelFactory
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    navController: NavHostController
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(
                        Screen.Detail.createRoute(-1)
                    )
                },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.add_setoran)
                )
            }
        }
    ) { innerPadding ->
        ScreenContent(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenContent(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val factory = ViewModelFactory(context)
    val viewModel: MainViewModel = viewModel(factory = factory)

    val data = viewModel.data.collectAsState()

    val dataStore = SettingsDataStore(context)
    val isList = dataStore.layoutFlow.collectAsState(initial = true)

    val scope = rememberCoroutineScope()
    var showThemeDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.app_name)
                    )
                },
                actions = {
                    IconButton(
                        onClick = {
                            scope.launch {
                                dataStore.saveLayout(!isList.value)
                            }
                        }
                    ) {
                        Icon(
                            imageVector =
                                if (isList.value)
                                    Icons.Default.GridView
                                else
                                    Icons.Default.ViewList,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }

                    IconButton(
                        onClick = {
                            showThemeDialog = true
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Palette,
                            contentDescription = "Pilih Tema",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }

                    IconButton(
                        onClick = {
                            navController.navigate(Screen.RecycleBin.route)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Recycle Bin",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { padding ->

        if (data.value.isEmpty()) {
            Column(
                modifier = modifier
                    .padding(padding)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.empty_list)
                )
            }
        } else {
            if (isList.value) {
                LazyColumn(
                    modifier = modifier
                        .padding(padding)
                        .fillMaxSize()
                ) {
                    items(data.value) { item ->
                        ListItem(
                            setoran = item,
                            onClick = {
                                navController.navigate(
                                    Screen.Detail.createRoute(item.id)
                                )
                            }
                        )

                        HorizontalDivider()
                    }
                }
            } else {
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(2),
                    modifier = modifier
                        .padding(padding)
                        .fillMaxSize()
                ) {
                    items(data.value) { item ->
                        GridItem(
                            setoran = item,
                            onClick = {
                                navController.navigate(
                                    Screen.Detail.createRoute(item.id)
                                )
                            }
                        )
                    }
                }
            }
        }
    }

    if (showThemeDialog) {
        AlertDialog(
            onDismissRequest = {
                showThemeDialog = false
            },
            title = {
                Text(text = "Pilih Tema")
            },
            text = {
                Column {
                    TextButton(
                        onClick = {
                            scope.launch {
                                dataStore.saveTheme(0)
                            }
                            showThemeDialog = false
                        }
                    ) {
                        Text("Hijau")
                    }

                    TextButton(
                        onClick = {
                            scope.launch {
                                dataStore.saveTheme(1)
                            }
                            showThemeDialog = false
                        }
                    ) {
                        Text("Biru")
                    }

                    TextButton(
                        onClick = {
                            scope.launch {
                                dataStore.saveTheme(2)
                            }
                            showThemeDialog = false
                        }
                    ) {
                        Text("Ungu")
                    }

                    TextButton(
                        onClick = {
                            scope.launch {
                                dataStore.saveTheme(3)
                            }
                            showThemeDialog = false
                        }
                    ) {
                        Text("Oranye")
                    }
                }
            },
            confirmButton = {}
        )
    }
}

@Composable
fun ListItem(
    setoran: Setoran,
    onClick: () -> Unit
) {
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
            text = stringResource(
                R.string.label_surah,
                setoran.surah
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = stringResource(
                R.string.label_ayat,
                setoran.ayat
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        if (setoran.catatan.isNotBlank()) {
            Text(
                text = setoran.catatan,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun GridItem(
    setoran: Setoran,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = setoran.namaSiswa,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = stringResource(
                    R.string.label_surah,
                    setoran.surah
                )
            )

            Text(
                text = stringResource(
                    R.string.label_ayat,
                    setoran.ayat
                )
            )

            if (setoran.catatan.isNotBlank()) {
                Text(
                    text = setoran.catatan
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    SiMukminTheme {
        MainScreen(
            navController = rememberNavController()
        )
    }
}