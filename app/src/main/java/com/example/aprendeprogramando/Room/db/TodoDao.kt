package com.example.aprendeprogramando.Room.db

import androidx.room.*
import com.example.aprendeprogramando.Room.db.Todo

// La anotación @Dao indica que esta interfaz es un Data Access Object (DAO) de Room.
@Dao
interface TodoDao {
    // Método que realiza una consulta para obtener todas las tareas ordenadas por fecha de creación ascendente.
    @Query("select * from todos order by created_at asc")
    fun getAll(): MutableList<Todo>

    // Método de inserción que utiliza la estrategia de conflicto REPLACE para reemplazar una tarea
    // existente con la misma clave primaria.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun post(todo: Todo)

    // Método de actualización que actualiza una tarea existente en la base de datos.
    @Update
    fun update(todo: Todo)

    // Método de eliminación que elimina una tarea de la base de datos.
    @Delete
    fun delete(todo: Todo)
}

