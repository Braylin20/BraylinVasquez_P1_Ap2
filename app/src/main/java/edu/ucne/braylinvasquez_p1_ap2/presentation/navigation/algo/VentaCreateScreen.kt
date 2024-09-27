package edu.ucne.braylinvasquez_p1_ap2.presentation.navigation.algo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import edu.ucne.braylinvasquez_p1_ap2.data.local.entities.VentaEntity
import edu.ucne.braylinvasquez_p1_ap2.ui.theme.BraylinVasquez_P1_Ap2Theme

@Composable
fun VentaCreateScreen(
    viewModel: VentaViewModel = hiltViewModel(),
    onBack: () -> Unit,
    ventaId: Int
) {
    LaunchedEffect(key1 = ventaId) {
        if(ventaId>0){
            viewModel.selectedVenta(ventaId)
        }
    }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    VentaCreateBodyScreen(
        uiState = uiState,
        onBack = onBack,
        save = viewModel::save,
        delete = viewModel::delete,
        onClienteChange = viewModel::onClienteChange,
        onPrecioChange = viewModel::onPrecioChange,
        onCantidadGalonesChange = viewModel::onCantidadGalonesChange,
        onDescuentoChange = viewModel::onDescuentoChange,
        onTotalDescuentoChange = viewModel::onTotalDescuentoChange,
        onTotalChange = viewModel::onTotalChange,
        ventaId = ventaId
    )
}


@Composable
fun VentaCreateBodyScreen(
    uiState: UiState,
    onBack: () -> Unit,
    save: () -> Unit,
    delete: (VentaEntity) -> Unit,
    onClienteChange: (String) -> Unit,
    onPrecioChange: (Double) -> Unit,
    onCantidadGalonesChange: (Double) -> Unit,
    onDescuentoChange: (Double) -> Unit,
    onTotalDescuentoChange: (Double, Double, Double) -> Unit,
    onTotalChange: (Double) -> Unit,
    ventaId: Int

) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onBack
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Save"
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Registro de Venta",
                    fontSize = 28.sp
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                OutlinedTextField(
                    value = uiState.cliente?:"",
                    onValueChange = onClienteChange,
                    label = { Text("Cliente") },
                    modifier = Modifier.fillMaxWidth(),
                    isError = uiState.message != null && uiState.message == "Cliente no puede estar vacío",
                )
                if (uiState.messageCliente != null) {
                    Text(
                        text = uiState.messageCliente,
                        color = Color.Red,
                        fontSize = 14.sp
                    )
                }

                OutlinedTextField(
                    value = uiState.cantidadGalones?.toString()?:"",
                    onValueChange = {onCantidadGalonesChange(it.toDouble())},
                    label = { Text("Cantidad de Galones") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                )
                if (uiState.messageGalones != null) {
                    Text(
                        text = uiState.messageGalones,
                        color = Color.Red,
                        fontSize = 14.sp
                    )
                }
                OutlinedTextField(
                    value = uiState.descuento?.toString()?:"",
                    onValueChange = {onDescuentoChange(it.toDouble())},
                    label = { Text("Descuento") },
                    modifier = Modifier.fillMaxWidth(),
                    readOnly = true
                )
                OutlinedTextField(
                    label = { Text("Precio") },
                    value = uiState.precio?.toString()?:"",
                    onValueChange = {onPrecioChange(it.toDouble())},
                    modifier = Modifier.fillMaxWidth(),
                    readOnly = true
                )
                OutlinedTextField(
                    value = uiState.totalDescuento?.toString()?:"",
                    onValueChange = {onTotalDescuentoChange(
                        uiState.descuento?:0.0,
                        uiState.precio?:0.0,
                        uiState.cantidadGalones?:0.0
                    )},
                    label = { Text("Total Descontado") },
                    modifier = Modifier.fillMaxWidth()
                )
                if (uiState.messageTotalDescuento != null) {
                    Text(
                        text = uiState.messageTotalDescuento,
                        color = Color.Red,
                        fontSize = 14.sp
                    )
                }
                OutlinedTextField(
                    value = uiState.total?.toString()?:"",
                    onValueChange = {onTotalChange(it.toDouble())},
                    label = { Text("Total") },
                    modifier = Modifier.fillMaxWidth(),
                    readOnly = true
                )
                if (uiState.messageTotal != null) {
                    Text(
                        text = uiState.messageTotal,
                        color = Color.Red,
                        fontSize = 14.sp
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            ){
                OutlinedButton(
                    onClick = save
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Save")
                    Text("Guardar")
                }
                if(ventaId> 0){
                    OutlinedButton(
                        onClick = {
                            delete(VentaEntity(
                                ventaId = uiState.ventaId,
                                cliente = uiState.cliente,
                                cantidadGalones = uiState.cantidadGalones,
                                descuento = uiState.descuento,
                                precio = uiState.precio,
                                totalDescuento = uiState.totalDescuento,
                                total = uiState.total

                            ))
                        }
                    ){
                        Text("Delete")
                    }

                }
            }
        }

    }
}


@Preview(showSystemUi = true)
@Composable
private fun VentaScreenPreview() {
    BraylinVasquez_P1_Ap2Theme {
        VentaCreateBodyScreen(
            uiState = UiState(),
            onBack = {},
            save = {},
            delete = {},
            onClienteChange = {},
            onPrecioChange = {},
            onCantidadGalonesChange = {},
            onDescuentoChange = {},
            onTotalDescuentoChange = { _, _, _ ->},
            onTotalChange = {},
            ventaId = 0
        )
    }

}