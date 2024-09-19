package edu.ucne.braylinvasquez_p1_ap2.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Algos")
data class AlgoEntity(
    @PrimaryKey
    val algoId: Int? = null,
)