package com.example.aprendeprogramando.apijuegos.components


import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.aprendeprogramando.apijuegos.models.VideoJuegosLista
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextLayoutResult
import com.example.aprendeprogramando.R


// Función composable que muestra la barra superior de la aplicación
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(titulo: String) {
    CenterAlignedTopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically){
                Image(
                    modifier = Modifier
                        .size(dimensionResource(R.dimen.image_size))
                        .padding(dimensionResource(id = R.dimen.padding_small)),
                    painter = painterResource(R.drawable.ap),
                    contentDescription = null
                )

                Text(
                    text = titulo,
                    color = Color.Black,
                    fontWeight = FontWeight.ExtraBold as FontWeight,
                    onTextLayout = { layoutResult: TextLayoutResult -> }
                )
            }

        },
//        backgroundColor = Color(Constantes.COLOR_NEGRO),
//        contentColor = Color.White
    )
}

// Función composable que muestra un card de juego
@Composable
fun CardJuego(juego: VideoJuegosLista, onClick: () -> Unit) {
    val context = LocalContext.current // Se obtiene el contexto actual

    // Card con forma redondeada y sombra, con posibilidad de hacer clic
    Card(
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
            .padding(8.dp)
            .shadow(40.dp)
            .clickable {
                Toast.makeText(context, "Estamos trabajando", Toast.LENGTH_SHORT).show() // Se muestra un mensaje al hacer clic
                onClick() // Se ejecuta la acción onClick
            }
    ) {
        Column {
            InicioImagen(imagen = juego.imagen) // Se muestra la imagen del juego
        }
    }
}

// Función composable que muestra una imagen de inicio
@Composable
fun InicioImagen(imagen: String) {
    val painter = rememberImagePainter(data = imagen) // Se carga la imagen con rememberImagePainter

    // Imagen con contenido recortado y altura fija
    Image(
        painter = painter,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxSize()
            .height(250.dp)
    )
}

