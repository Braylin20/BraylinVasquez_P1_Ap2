package edu.ucne.braylinvasquez_p1_ap2.presentation.navigation.algo

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import edu.ucne.braylinvasquez_p1_ap2.data.local.entities.VentaEntity
import edu.ucne.braylinvasquez_p1_ap2.ui.theme.BraylinVasquez_P1_Ap2Theme


@Composable
fun VentaListScreen(
    viewModel: VentaViewModel = hiltViewModel(),
    onGoCreate: () -> Unit,
    onGoEdit: (Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    VentaListBodyScreen(
        uiState = uiState,
        onGoCreate = onGoCreate,
        onGoEdit = onGoEdit
    )
}


@Composable
fun VentaListBodyScreen(
    uiState: UiState,
    onGoCreate: () -> Unit,
    onGoEdit: (Int) -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onGoCreate
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        }
    ) {innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
        ) {
            Text(
                text = "Lista de Ventas",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    ,
                horizontalArrangement = Arrangement.Center
            ){
                Text(
                    text = "Id",
                    modifier = Modifier.weight(0.5f)
                )
                Text(
                    text = "Cliente",
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "Galones",
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text ="Precio",
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "Descuento",
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "Total",
                    modifier = Modifier.weight(1f)
                )
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
            ){
                items(uiState.ventas){ venta ->
                    VentaListRowScreen(
                        venta = venta,
                        onGoEdit = onGoEdit
                    )
                }
            }
        }
    }

}


@Composable
fun VentaListRowScreen(
    venta: VentaEntity,
    onGoEdit: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                onGoEdit(venta.ventaId ?: 0)
            }
    ){
        Text(
            text = venta.ventaId?.toString()?:"",
            modifier = Modifier.weight(0.5f)
        )
        Text(
            text = venta.cliente?:"",
            modifier = Modifier.weight(1.5f)
        )
        Text(
            text = venta.cantidadGalones?.toString()?:"",
            modifier = Modifier.weight(1f)
        )
        Text(
            text = venta.precio?.toString()?:"",
            modifier = Modifier.weight(1f)
        )
        Text(
            text = venta.totalDescuento?.toString()?:"",
            modifier = Modifier.weight(1f)
        )
        Text(
            text = venta.total?.toString()?:"",
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun VentaListScreenPreview() {
    BraylinVasquez_P1_Ap2Theme {
        VentaListBodyScreen(
            onGoCreate = {},
            onGoEdit = {},
            uiState = UiState()
        )
    }
}