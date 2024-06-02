package com.example.aprendeprogramando.Room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


// La anotación @Database indica que esta clase es una base de datos Room.
// Se especifica que solo tiene una entidad (Todo) y la versión de la base de datos es 1.
// exportSchema = false evita la exportación del esquema de la base de datos.
@Database(entities = [Todo::class], version = 1, exportSchema = false)
@TypeConverters(DateTimeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    // Este método abstracto proporciona acceso al DAO (Data Access Object) asociado con la entidad Todo.
    abstract fun todoDao(): TodoDao

    // Companion object que contiene constantes relacionadas con la base de datos.
    companion object {
        // Constante que representa el nombre de la base de datos.
        const val NAME = "todos"
    }
}
