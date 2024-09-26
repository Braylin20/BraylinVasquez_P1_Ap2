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

    fun save() {
        viewModelScope.launch {
            if(
                uiState.value.cliente.isNullOrBlank()||
                uiState.value.cantidadGalones == null||
                uiState.value.descuento == null||
                uiState.value.precio == null||
                uiState.value.totalDescuento == null||
                uiState.value.total == null
                ){
                _uiState.update {
                    it.copy(message = "Todos los campos son requeridos")
                }
                return@launch
            }
            ventaRepository.save(uiState.value.toEntity())
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
            it.copy(cliente = cliente)
        }
    }

    fun onCantidadGalonesChange(cantidadGalones: Double) {
        val newCantidadGalones = cantidadGalones
        _uiState.update {
            it.copy(cantidadGalones = cantidadGalones)
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

    fun onTotalDescuentoChange(descuento: Double, precio: Double, cantidadGalones: Double) {
        val cantGalonesDescuento = cantidadGalones *2
        val totalDescuento = (precio * cantidadGalones) - cantGalonesDescuento
        _uiState.update {
            it.copy(totalDescuento = totalDescuento)
        }
    }

    fun onTotalChange(cantidadGalones: Double) {
        val newTotal = cantidadGalones * 132.6
        viewModelScope.launch {
            _uiState.update {
                it.copy(total = newTotal)
            }
        }

    }
}