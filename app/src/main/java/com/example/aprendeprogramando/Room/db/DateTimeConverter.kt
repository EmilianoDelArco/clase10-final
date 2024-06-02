package com.example.aprendeprogramando.Room.db

import androidx.room.TypeConverter
import java.util.*

// Esta clase actúa como convertidor de tipos para Room, permitiendo almacenar y recuperar
// objetos de tipo Date en la base de datos como valores de tipo Long.
class DateTimeConverter {

    // Método de conversión: Convierte un valor Long en un objeto Date.
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        // Utiliza el operador let para evitar una conversión nula si el valor es null.
        return value?.let { Date(it) }
    }

    // Método de conversión: Convierte un objeto Date en un valor Long.
    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        // Retorna el tiempo en milisegundos del objeto Date, o null si el objeto es null.
        return date?.time?.toLong()
    }
}