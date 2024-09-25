package edu.ucne.braylinvasquez_p1_ap2.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Ventas")
data class VentaEntity(
    @PrimaryKey
    val ventaId: Int? = null,
    val cantdidadGalones: Double? = null,
    val descuento: Double? = null,
    val precio: Double? = null,
    val totalDescuento: Double? = null,
    val total: Double? = null
)