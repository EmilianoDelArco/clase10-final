package com.example.aprendeprogramando.Room.items

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aprendeprogramando.Room.db.Todo
import com.example.aprendeprogramando.R
import com.example.aprendeprogramando.ui.theme.md_theme_light_primaryContainer
import java.text.SimpleDateFormat
import java.util.*

// Composable que representa un elemento visual para mostrar información de una tarea (Todo).
@Composable
fun TodoItem(
    todo: Todo,
    // Parámetro opcional para manejar el evento de clic en el botón de edición.
    onEditClick: (todo: Todo) -> Unit = {},
    // Parámetro opcional para manejar el evento de clic en el botón de eliminación.
    onClick: (todo: Todo) -> Unit = {}
) {
    // Formato de fecha para mostrar la fecha de creación de la tarea.
    val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.JAPAN)

    // Columna que contiene la estructura visual de la tarea.
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(md_theme_light_primaryContainer)
            .border(BorderStroke(2.dp, Color.Blue))
            .padding(dimensionResource(R.dimen.padding_medium))
    ) {
        // Fila que contiene el título de la tarea y los iconos de edición y eliminación.
        Row(
            modifier = Modifier
                .fillMaxWidth(),

            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Texto del título de la tarea.
            Text(
                text = todo.title,
                modifier = Modifier.weight(1f)
                    .padding(bottom = 4.dp),
                style = MaterialTheme.typography.displayMedium,
            )

            // Icono de lápiz para editar la tarea.
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Editar",
                tint = Color.Gray,
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        // Llama al evento de clic en el botón de edición.
                        onEditClick(todo)
                    }
            )

            // Icono de basura para eliminar la tarea.
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Eliminar",
                tint = Color.Red,
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        // Llama al evento de clic en el botón de eliminación.
                        onClick(todo)
                    }
            )
        }

        // Texto que muestra la fecha de creación de la tarea.
        Text(
            text = "${stringResource(id = R.string.todo_created_at)} ${sdf.format(todo.created_at)}",
            fontSize = 12.sp,
            textAlign = TextAlign.Right,
            modifier = Modifier.fillMaxWidth()
        )
    }

    // Espaciador para separar visualmente los elementos.
    Spacer(modifier = Modifier.height(16.dp))
}