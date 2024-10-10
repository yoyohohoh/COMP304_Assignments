package com.example.yiuyiuyoyoho_comp304sec003_lab02.views

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.yiuyiuyoyoho_comp304sec003_lab02.data.Status
import com.example.yiuyiuyoyoho_comp304sec003_lab02.data.Task
import com.example.yiuyiuyoyoho_comp304sec003_lab02.navigation.Activities
import com.example.yiuyiuyoyoho_comp304sec003_lab02.ui.StatusDot
import com.example.yiuyiuyoyoho_comp304sec003_lab02.viewmodel.TasksViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale


@Composable
fun TaskList(
    onTaskClicked: (Task) -> Unit,
    tasksViewModel: TasksViewModel) {
    val tasks by tasksViewModel.tasks.collectAsState()
    val navController = rememberNavController()
    LazyColumn(
        modifier = Modifier.padding(10.dp)
    ) {
        items(tasks) { task ->
            TaskItem(task, tasksViewModel, onTaskClicked)
        }
    }
}

@Composable
fun TaskItem(task: Task,tasksViewModel: TasksViewModel, onTaskClicked: (Task) -> Unit) {
    val expanded = remember { mutableStateOf(false) }
    ElevatedCard(
        modifier = Modifier
            .clickable {
                onTaskClicked(task)
            }
            .fillMaxWidth()
            .padding(6.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, top = 12.dp, end = 12.dp, bottom = 12.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ){
                Text(
                    text = task.title,
                    color = Color.Black,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = task.dueDate.format(DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.ENGLISH)),
                    color = Color.Black,
                    fontSize = 12.sp
                )
            }

            Text(
                text = task.status.name,
                fontSize = 18.sp,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .background(
                        color = when (task.status) {
                            Status.NEW -> Color(0xFF89e18c)
                            Status.PENDING -> Color(0xFFcb5f5f)
                            Status.CLOSED -> Color.Gray
                        }, shape = RoundedCornerShape(10.dp)
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
                    onClick = { tasksViewModel.updateTaskStatus(task, Status.NEW)
                        expanded.value = false},
                    trailingIcon = {StatusDot(Status.NEW)}
                )
                DropdownMenuItem(
                    text = { Text("PENDING") },
                    onClick = { tasksViewModel.updateTaskStatus(task, Status.PENDING)
                        expanded.value = false},
                    trailingIcon = {StatusDot(Status.PENDING)}
                )
                DropdownMenuItem(
                    text = { Text("CLOSED") },
                    onClick = { tasksViewModel.updateTaskStatus(task, Status.CLOSED)
                        expanded.value = false},
                    trailingIcon = {StatusDot(Status.CLOSED)}
                )

            }
        }
    }
}


