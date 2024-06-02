package com.example.aprendeprogramando.apijuegos.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aprendeprogramando.apijuegos.models.VideoJuegosLista
import com.example.aprendeprogramando.apijuegos.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// Clase ViewModel que se encarga de manejar la lógica de obtención de juegos desde la API
class VideosJuegosViewModel : ViewModel() {
    private val _juegos = MutableStateFlow<List<VideoJuegosLista>>(emptyList()) // MutableStateFlow para almacenar la lista de juegos
    val juegos = _juegos.asStateFlow() // StateFlow para exponer la lista de juegos de forma inmutable

    init {
        obtenerJuegos() // Llamada a la función para obtener los juegos al inicializar el ViewModel
    }

    // Función para obtener la lista de juegos desde la API
    private fun obtenerJuegos() {
        viewModelScope.launch(Dispatchers.IO) { // Se ejecuta en un hilo de fondo
            withContext(Dispatchers.Main) { // Se vuelve al hilo principal para actualizar la UI
                // Llamada a la API a través de RetrofitClient para obtener la lista de juegos
                val response = RetrofitClient.retrofit.obtenerJuegos()
                // Se actualiza el valor de _juegos con la lista de juegos obtenida de la respuesta
                _juegos.value = response.body()?.listaVideoJuegos ?: emptyList()
            }
        }
    }
}

