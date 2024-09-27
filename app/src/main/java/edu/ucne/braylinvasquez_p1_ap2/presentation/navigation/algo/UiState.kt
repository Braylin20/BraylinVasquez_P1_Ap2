package edu.ucne.braylinvasquez_p1_ap2.presentation.navigation.algo

import edu.ucne.braylinvasquez_p1_ap2.data.local.entities.VentaEntity

data class UiState(
    val ventaId: Int? = null,
    val cliente: String? = null,
    val cantidadGalones: Double? = null,
    val descuento: Double? = 2.0,
    val precio: Double? = 132.6,
    val totalDescuento: Double? = null,
    val total: Double? = null,
    val message: String? = null,
    val messageCliente: String? = null,
    val messageGalones: String? = null,
    val messageDescuento: String? = null,
    val messagePrecio: String? = null,
    val messageTotalDescuento: String? = null,
    val messageTotal: String? = null,
    val ventas: List<VentaEntity> = emptyList()
)

fun UiState.toEntity() = VentaEntity(
    ventaId = ventaId,
    cliente = cliente,
    cantidadGalones = cantidadGalones,
    descuento = descuento,
    precio = precio,
    totalDescuento = totalDescuento,
    total = total
)


