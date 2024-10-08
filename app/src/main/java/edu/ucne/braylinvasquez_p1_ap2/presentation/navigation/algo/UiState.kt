package edu.ucne.braylinvasquez_p1_ap2.presentation.navigation.algo

import edu.ucne.braylinvasquez_p1_ap2.data.local.entities.VentaEntity

data class UiState(
    val ventaId: Int? = null,
    val cliente: String? = null,
    val cantidadGalones: Double? = null,
    val descuento: Double? = 2.0,
    val precio: Double? = 132.6,
    val totalDescuento: Double? = 1.0,
    val total: Double? = 1.0,
    val message: String? = null,
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


