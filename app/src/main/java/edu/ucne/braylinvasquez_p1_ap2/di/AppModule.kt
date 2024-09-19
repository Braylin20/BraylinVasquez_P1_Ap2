package edu.ucne.braylinvasquez_p1_ap2.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import edu.ucne.braylinvasquez_p1_ap2.data.local.database.Parcial1DataBase
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    @Singleton
    fun providePrimerParcialDb(@ApplicationContext appContext: Context)=
        Room.databaseBuilder(
            appContext,
            Parcial1DataBase::class.java,
            "PrimerParcial.db"
        ).fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideAlgoDao(parcial1DataBase: Parcial1DataBase) = parcial1DataBase.algoDao()
}