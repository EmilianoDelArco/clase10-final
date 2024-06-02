package com.example.aprendeprogramando.vistas

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.aprendeprogramando.Room.db.Todo
import com.example.aprendeprogramando.Room.items.MainApplication
import com.example.aprendeprogramando.Room.items.TodoItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.aprendeprogramando.R

@Composable
fun TaskListScreen() {
    MainScreen(todoList = todoList)
    // Agregar código para mostrar y administrar tareas
    loadToDo()
}
private val dao = MainApplication.database.todoDao() ?: throw IllegalStateException("Database not initialized")
private var todoList = mutableStateListOf<Todo>()
private var scope = MainScope()

private fun loadToDo() {
    scope.launch {
        withContext(Dispatchers.Default) {
            dao.getAll().forEach { todo ->
                todoList.add(todo)
            }
        }
    }
}

private fun postTodo(title: String) {
    scope.launch {
        withContext(Dispatchers.Default) {
            dao.post(Todo(title = title))

            todoList.clear()
            loadToDo()
        }
    }
}

private fun updateTodo(todo: Todo) {
    scope.launch {
        withContext(Dispatchers.Default) {
            dao.update(todo)
            todoList.clear()
            loadToDo()
        }
    }
}

private fun deleteTodo(todo: Todo) {
    scope.launch {
        withContext(Dispatchers.Default) {
            dao.delete(todo)
            todoList.clear()
            loadToDo()
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(todoList: SnapshotStateList<Todo>) {
    //    val context = LocalContext.current
    val keyboardController: SoftwareKeyboardController? = LocalSoftwareKeyboardController.current
    var text: String by remember {
        mutableStateOf("")
    }
    var isEditDialogVisible by remember { mutableStateOf(false) }
    var editingTodo by remember { mutableStateOf<Todo?>(null) }


    Column(
        modifier = Modifier.clickable {
            keyboardController?.hide()
        }
    ) {
        TopAppBar(
            title = { Text(text = stringResource(id = R.string.main_title)) },
            modifier = Modifier.background(Color.Magenta)

        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {

            items(todoList) { todo ->
                key(todo.id) {
                    TodoItem(
                        todo = todo,
                        onClick = {
                            deleteTodo(todo)
                        },
                        onEditClick = {
                            editingTodo = todo
                            isEditDialogVisible = true

                        }
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier
                    .border(
                        BorderStroke(2.dp, Color.Blue)
                    ),
                //                    .background(Color.White),


                label = { Text(text = stringResource(id = R.string.main_new_todo)) }
            )

            Spacer(modifier = Modifier.size(18.dp))

            IconButton(
                onClick = {
                    if (text.isEmpty()) return@IconButton

                    postTodo(text)
                    text = ""
                },
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .size(56.dp)
                    .background(Color.Magenta)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.main_add_todo),
                    tint = Color.White
                )
            }

        }

        if (isEditDialogVisible) {
            EditTodoDialog(
                todo = editingTodo!!,
                onEditTodo = { editedTodo ->
                    // Lógica para guardar la tarea editada
                    // Puedes actualizar la lista de tareas o realizar otras acciones necesarias
                    // En este ejemplo, simplemente imprimo la tarea editada
                    println("Tarea editada: ${editedTodo.title}")

                    // Actualizar la tarea en la base de datos
                    updateTodo(editedTodo)

                    // Actualizar la lista observable
                    val updatedList = todoList.toMutableList()
                    val index = updatedList.indexOfFirst { it.id == editedTodo.id }
                    if (index != -1) {
                        updatedList[index] = editedTodo
                        todoList.clear()
                        todoList.addAll(updatedList)
                    }


                    // Cerrar el diálogo de edición
                    isEditDialogVisible = false
                    editingTodo = null
                },
                onDismiss = {
                    // Cerrar el diálogo de edición
                    isEditDialogVisible = false
                    editingTodo = null
                }
            )
        }
    }
}
@Composable
fun EditTodoDialog(
    todo: Todo,
    onEditTodo: (Todo) -> Unit,
    onDismiss: () -> Unit
) {
    var editedTitle by remember { mutableStateOf(todo.title) }

    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                OutlinedTextField(
                    value = editedTitle,
                    onValueChange = { editedTitle = it },
                    label = { Text("Nuevo título") }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(onClick = {
                        onEditTodo(todo.copy(title = editedTitle))
                        onDismiss()
                    }) {
                        Text("Guardar")
                    }
                }
            }
        }
    }
}