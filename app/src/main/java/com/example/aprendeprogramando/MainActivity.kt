package com.example.aprendeprogramando

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.aprendeprogramando.apijuegos.viewmodels.VideosJuegosViewModel
import com.example.aprendeprogramando.ui.theme.AprendeProgramandoTheme
import com.example.aprendeprogramando.vistas.ApiGames
import com.example.aprendeprogramando.vistas.HomeScreen
import com.example.aprendeprogramando.vistas.TaskListScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: VideosJuegosViewModel by viewModels()
        setContent {
            AprendeProgramandoTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {

                    val navController = rememberNavController()
                    AprendeProgramandoApp(navController = navController, viewModel)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AprendeProgramandoAppBar(
    currentScreen: String,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(currentScreen) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp,modifier = Modifier
                    .background(Color.Magenta)) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        }
    )
}

@Composable
fun AprendeProgramandoApp(
    navController: NavHostController = rememberNavController(),viewModel:VideosJuegosViewModel
) {
    Scaffold(
        topBar = {
            AprendeProgramandoAppBar(
                currentScreen = navController.currentBackStackEntryAsState().value?.destination?.route ?: "Start",
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "Inicio",
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
            composable("Inicio") {
                HomeScreen(
                    navController = navController,
                    onNextButtonClicked = {
                        navController.navigate("Lista de Tareas")
                    },
                    modifier = Modifier
                        .fillMaxSize()

                )
            }
            composable("Lista de Tareas") {
                TaskListScreen()
            }
            composable("Juegos") {
                ApiGames(navController = navController,viewModel = viewModel)
            }
        }
    }
}