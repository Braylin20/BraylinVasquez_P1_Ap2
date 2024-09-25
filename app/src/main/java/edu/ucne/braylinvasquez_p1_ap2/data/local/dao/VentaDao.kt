package edu.ucne.braylinvasquez_p1_ap2.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import edu.ucne.braylinvasquez_p1_ap2.data.local.entities.VentaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface VentaDao {
    @Upsert
    suspend fun save(algo: VentaEntity)

    @Query("Select * from Ventas where ventaId = :id")
    suspend fun find(id: Int): VentaEntity

    @Delete
    suspend fun delete(algo: VentaEntity)

    @Query("Select * from Ventas")
    fun getAll(): Flow<List<VentaEntity>>
}