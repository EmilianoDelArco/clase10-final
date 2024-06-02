package com.example.aprendeprogramando.Room.items

import android.app.Application
import androidx.room.Room
import com.example.aprendeprogramando.Room.db.AppDatabase

// Clase que extiende Application y se utiliza para inicializar y proporcionar acceso global
// a la base de datos de la aplicación.
class MainApplication : Application() {
    // Companion object que contiene una propiedad estática para acceder a la base de datos de la aplicación.
    companion object {
        // Propiedad estática que representa la instancia de la base de datos de la aplicación.
        lateinit var database: AppDatabase
    }

    // Método que se llama cuando la aplicación se inicia.
    override fun onCreate() {
        super.onCreate()

        // Inicializa la base de datos utilizando Room.databaseBuilder.
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            AppDatabase.NAME
        ).build()
    }
}
