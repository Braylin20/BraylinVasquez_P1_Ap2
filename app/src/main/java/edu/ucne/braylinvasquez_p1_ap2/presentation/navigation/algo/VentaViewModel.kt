package edu.ucne.braylinvasquez_p1_ap2.presentation.navigation.algo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.braylinvasquez_p1_ap2.data.local.entities.VentaEntity
import edu.ucne.braylinvasquez_p1_ap2.data.repository.VentaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VentaViewModel @Inject constructor(
    private val ventaRepository: VentaRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()


    init {
        getVentas()
    }

    private fun getVentas() {
        viewModelScope.launch {
            ventaRepository.getAll().collect { ventas ->
                _uiState.update {
                    it.copy(ventas = ventas)
                }
            }
        }
    }
    private fun isValid(): Boolean {
        var isValid = true
        if (uiState.value.cliente.isNullOrBlank()) {
            _uiState.update {
                it.copy(messageCliente = "Cliente no puede estar vacío")
            }
            isValid = false
        }
        if (uiState.value.cantidadGalones == null) {
            _uiState.update {
                it.copy(messageGalones = "Cantidad de galones no puede estar vacío")
            }
            isValid = false
        }
//        if(uiState.value.totalDescuento == null){
//            _uiState.update {
//                it.copy(messageTotalDescuento = "Total descuento no puede estar vacío")
//            }
//            isValid = false
//        }
//        if(uiState.value.total == null){
//            _uiState.update {
//                it.copy(messageTotal = "Total no puede estar vacío")
//            }
//            isValid = false
//        }
        return isValid
    }

    fun save() {
        if (isValid()){
            viewModelScope.launch {

                ventaRepository.save(uiState.value.toEntity())
            }
        }

    }

    fun delete(venta: VentaEntity) {
        viewModelScope.launch {
            ventaRepository.delete(venta)
            _uiState.update {
                it.copy(message = "Venta eliminado")
            }
        }
        nuevo()
    }

    private fun nuevo(){
        _uiState.update {
            it.copy(
                ventaId = null,
                cliente = "",
                cantidadGalones = null,
                descuento = null,
                precio = null,
                totalDescuento = null,
                total = null
            )
        }
    }
    fun selectedVenta(id: Int) {
        viewModelScope.launch {
            if (id > 0) {
                val venta = ventaRepository.find(id)
                _uiState.update {
                    it.copy(
                        ventaId = venta.ventaId,
                        cliente = venta.cliente,
                        cantidadGalones = venta.cantidadGalones,
                        descuento = venta.descuento,
                        precio = venta.precio,
                        totalDescuento = venta.totalDescuento,
                        total = venta.total
                    )
                }
            }
        }
    }

    fun onClienteChange(cliente: String) {
        _uiState.update {
            it.copy(
                cliente = cliente,
                messageCliente = if(cliente.isNotBlank()) null else "Cliente no puede estar vacío"
            )

        }
    }

    fun onCantidadGalonesChange(cantidadGalones: Double) {
        val totalDescuento = cantidadGalones * (uiState.value.descuento!!)
        _uiState.update {
            it.copy(
                cantidadGalones = cantidadGalones,
                totalDescuento = totalDescuento,
                messageGalones = if(cantidadGalones > 0) null else "Cantidad de galones no puede estar vacío"
            )
        }
    }

    fun onDescuentoChange(descuento: Double) {
        val newDescuento = descuento
        _uiState.update {
            it.copy(descuento = descuento)
        }
    }

    fun onPrecioChange(precio: Double) {
        val newPrecio = precio.toDouble()
        _uiState.update {
            it.copy(precio = newPrecio)
        }
    }
//
//    fun onTotalDescuentoChange() {
//        val totalDecuento = (uiState.value.cantidadGalones!!) *(uiState.value.descuento!!)
//        _uiState.update {
//            it.copy(
//                totalDescuento = totalDecuento,
//            )
//        }
//    }

    fun onTotalChange(cantidadGalones: Double) {
        val newTotal = cantidadGalones * 132.6
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    total = newTotal,
                    messageTotal = if(newTotal > 0) null else "Total no puede estar vacío"
                )
            }
        }

    }
}