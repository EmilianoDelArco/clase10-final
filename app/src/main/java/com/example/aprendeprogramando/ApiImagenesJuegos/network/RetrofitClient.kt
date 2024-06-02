package com.example.aprendeprogramando.apijuegos.network

import com.example.aprendeprogramando.apijuegos.utils.Constantes
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Objeto que se encarga de crear y configurar la instancia de Retrofit para la comunicación con la API de juegos
object RetrofitClient {
    // Se utiliza lazy para inicializar la instancia de Retrofit de forma diferida
    val retrofit: APIGames by lazy {
        // Configuración de Retrofit
        Retrofit.Builder()
            .baseUrl(Constantes.BASE_URL) // Se establece la URL base de la API
            .addConverterFactory(GsonConverterFactory.create()) // Se añade un convertidor Gson para manejar la
                                                                // serialización y deserialización de datos
            .build()
            .create(APIGames::class.java) // Se crea una instancia de la interfaz APIGames para realizar las llamadas a la API
    }
}