package com.example.aprendeprogramando.apijuegos.models

import com.google.gson.annotations.SerializedName

// Clase que representa el modelo de datos de un videojuego
data class VideoJuegoModel(
    @SerializedName("count")
    val total: Int, // Número total de juegos
    @SerializedName("results")
    val listaVideoJuegos: List<VideoJuegosLista> // Lista de videojuegos
)

// Clase que representa un elemento de la lista de videojuegos
data class VideoJuegosLista(
    @SerializedName("id")
    val id: Int, // Identificador del juego
    @SerializedName("name")
    val nombre: String, // Nombre del juego
    @SerializedName("background_image")
    val imagen: String // URL de la imagen de fondo del juego
)


