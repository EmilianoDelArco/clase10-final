package com.example.aprendeprogramando.vistas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.aprendeprogramando.apijuegos.components.CardJuego
import com.example.aprendeprogramando.apijuegos.components.MainTopBar
import com.example.aprendeprogramando.apijuegos.viewmodels.VideosJuegosViewModel


@Composable
fun ApiGames(navController: NavController, viewModel: VideosJuegosViewModel) {
    Scaffold(
        topBar = {
            MainTopBar(titulo = "API JUEGOS") // Barra superior de la aplicación
        }
    ) {
        ContenidoInicioView(navController = navController, viewModel = viewModel, pad = it) // Contenido de la vista de inicio
    }
}

// Función composable que muestra el contenido de la vista de inicio
@Composable
fun ContenidoInicioView(navController: NavController, viewModel: VideosJuegosViewModel, pad: PaddingValues) {
    val juegos by viewModel.juegos.collectAsState() // Se obtiene la lista de juegos del ViewModel
    println(juegos.size)
    val context = LocalContext.current // Se obtiene el contexto actual

    Column(modifier = Modifier
        .padding(7.dp)
        .size(1000.dp)
        .verticalScroll(rememberScrollState())
    ) {
        // Recorrer la lista de juegos y mostrar cada uno como una card
        juegos.forEach { juego ->
            CardJuego(juego = juego) {
            }
        }
    }
}