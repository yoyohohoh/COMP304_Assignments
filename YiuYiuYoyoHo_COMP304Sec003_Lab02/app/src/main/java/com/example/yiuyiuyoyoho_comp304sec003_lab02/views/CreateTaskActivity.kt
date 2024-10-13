package com.example.yiuyiuyoyoho_comp304sec003_lab02.views

import EditTaskDueDate
import android.util.Log
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yiuyiuyoyoho_comp304sec003_lab02.data.Status
import com.example.yiuyiuyoyoho_comp304sec003_lab02.data.Task
import com.example.yiuyiuyoyoho_comp304sec003_lab02.navigation.Activities
import com.example.yiuyiuyoyoho_comp304sec003_lab02.ui.StatusDot
import com.example.yiuyiuyoyoho_comp304sec003_lab02.viewmodel.TasksViewModel
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTaskActivity(
    navigationToHomeActivity:() -> Unit,
    tasksViewModel: TasksViewModel) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var dueDate by remember { mutableStateOf(LocalDate.now()) }
    var status by remember { mutableStateOf(Status.NEW) }
    val expanded = remember { mutableStateOf(false) }

    val tasks by tasksViewModel.tasks.collectAsState()
    var id by remember { mutableStateOf(tasks.size + 1) }

    var newTask = Task(id, title, description, dueDate,status)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "New Task")

                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navigationToHomeActivity()},
                        content = {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    )
                }
            )
        },

        floatingActionButton = {
            FloatingActionButton(

                onClick = {
                    when {
                        newTask.title.isNotBlank() && newTask.description.isNotBlank() -> {
                            tasksViewModel.addTask(newTask)
                            navigationToHomeActivity()
                        }

                        newTask.title.isBlank() && newTask.description.isNotBlank() -> {
                            val taskWithDefaultTitle = Task(
                                newTask.id,
                                title = "New Task",
                                description = newTask.description,
                                dueDate = newTask.dueDate,
                                status = newTask.status
                            )
                            tasksViewModel.addTask(taskWithDefaultTitle)
                            navigationToHomeActivity()
                        }

                        newTask.title.isNotBlank() && newTask.description.isBlank() -> {
                            val taskWithDefaultDescription = Task(
                                newTask.id,
                                title = newTask.title,
                                description = "",
                                dueDate = newTask.dueDate,
                                status = newTask.status
                            )
                            tasksViewModel.addTask(taskWithDefaultDescription)
                            navigationToHomeActivity()
                        }

                        else -> {
                            navigationToHomeActivity()
                        }
                    }
                          },
                modifier = Modifier
                    .semantics { contentDescription = "Save Task" }
                    .size(80.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = "Save"
                )
            }
        },

        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                TextField(
                    value = title, // Use the mutable title state here
                    onValueChange = { newText -> title = newText },
                    placeholder = {
                        Text(
                            text = "Title"
                        )
                    },
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        //unfocusedIndicatorColor = Color.Transparent,
                        //disabledIndicatorColor = Color.Transparent,
                    ),
                    textStyle = TextStyle.Default.copy(fontSize = 24.sp)
                )
                TextField(
                    value = description, // Use the mutable title state here
                    onValueChange = { newText -> description = newText },
                    placeholder = {
                        Text(
                            text = "Description"
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                    ),
                    modifier = Modifier
                        .fillMaxWidth(),


                    )

                Row(
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(text = "Status  ")
                    Text(
                        text = status.name,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .background(
                                color = when (status) {
                                    Status.NEW -> Color(0xFF89e18c)
                                    Status.PENDING -> Color(0xFFcb5f5f)
                                    Status.CLOSED -> Color.Gray
                                },
                                shape = RoundedCornerShape(10.dp)
                            )
                            .padding(all = 6.dp)
                    )

                    IconButton(
                        onClick = { expanded.value = true },
                        modifier = Modifier
                    ) {
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown menu")
                    }


                    DropdownMenu(expanded = expanded.value, onDismissRequest = { expanded.value = false }) {
                        DropdownMenuItem(
                            text = { Text("Mark It As") },
                            onClick = { /* Handle edit! */ },
                        )
                        HorizontalDivider()
                        DropdownMenuItem(
                            text = { Text("NEW") },
                            onClick = {status = Status.NEW
                                expanded.value = false},
                            trailingIcon = { StatusDot(Status.NEW) }
                        )
                        DropdownMenuItem(
                            text = { Text("PENDING") },
                            onClick = {status = Status.PENDING
                                expanded.value = false},
                            trailingIcon = { StatusDot(Status.PENDING) }
                        )
                        DropdownMenuItem(
                            text = { Text("CLOSED") },
                            onClick = {status = Status.CLOSED
                                expanded.value = false},
                            trailingIcon = { StatusDot(Status.CLOSED) }
                        )
                    }

                    Text(text = "Due Date  ")

                    EditTaskDueDate(dueDate) { newDate ->
                        dueDate = newDate
                    }
                }
            }
        }
    )
}


//@Composable
//@Preview
//fun CreateTaskPreview(){
//    val tasksViewModel: TasksViewModel by viewModels()
//    CreateTaskActivity(tasksViewModel = tasksViewModel, navigationToHomeActivity = {})
//}