package edu.ucne.braylinvasquez_p1_ap2.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.ucne.braylinvasquez_p1_ap2.data.local.dao.VentaDao
import edu.ucne.braylinvasquez_p1_ap2.data.local.entities.VentaEntity

@Database(
    entities = [
        VentaEntity::class
    ],
    version = 1,
    exportSchema = false,

)
abstract class Parcial1DataBase:RoomDatabase() {
    abstract fun algoDao(): VentaDao
}