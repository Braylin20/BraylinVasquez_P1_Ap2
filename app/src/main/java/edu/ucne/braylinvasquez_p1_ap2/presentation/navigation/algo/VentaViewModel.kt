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
): ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState= _uiState.asStateFlow()


    init {
        getVentas()
    }

    private fun getVentas() {
        viewModelScope.launch {
            ventaRepository.getAll().collect{ ventas ->
                _uiState.update {
                    it.copy(ventas = ventas)
                }
            }
        }
    }

    fun save(){
        viewModelScope.launch {
            ventaRepository.save(uiState.value.toEntity())
        }
    }

    fun delete(venta: VentaEntity){
        viewModelScope.launch {
            ventaRepository.delete(venta)
        }
    }

    fun selectedVenta(id: Int){
        viewModelScope.launch {
            if(id>0){
                val venta = ventaRepository.find(id)
                _uiState.update {
                    it.copy(
                        ventaId = venta.ventaId,
                        cliente = venta.cliente,
                        cantdidadGalones = venta.cantdidadGalones,
                        descuento = venta.descuento,
                        precio = venta.precio,
                        totalDescuento = venta.totalDescuento,
                        total = venta.total
                    )
                }
            }
        }
    }

    fun onClienteChange(cliente: String){
        _uiState.update {
            it.copy(cliente = cliente)
        }
    }
    fun onCantidadGalonesChange(cantidadGalones: Double){
        _uiState.update {
            it.copy(cantdidadGalones = cantidadGalones)
        }
    }

    fun onDescuentoChange(descuento: Double){
        _uiState.update {
            it.copy(descuento = descuento)
        }
    }
    fun onPrecioChange(precio: Double){
        _uiState.update {
            it.copy(precio = precio)
        }
    }
    fun onTotalDescuentoChange(totalDescuento: Double){
        _uiState.update {
            it.copy(totalDescuento = totalDescuento)
        }
    }

    fun onTotalChange(total: Double){
        _uiState.update {
            it.copy(total = total)
        }
    }
}