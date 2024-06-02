package com.example.aprendeprogramando.Room.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

// La anotación @Entity indica que esta clase es una entidad de Room,
// que representará una tabla en la base de datos.
@Entity(tableName = "todos")
data class Todo(
    // La anotación @PrimaryKey especifica que 'id' será la clave primaria de la tabla,
    // y autoGenerate = true indica que se generará automáticamente.
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    // Campo que representa el título de la tarea.
    val title: String,

    // Campo que representa la fecha de creación de la tarea.
    // Se establece un valor predeterminado en la fecha actual.
    val created_at: Date = Date()
)