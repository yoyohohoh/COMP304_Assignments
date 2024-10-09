package com.example.yiuyiuyoyoho_comp304sec003_lab02.views


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.yiuyiuyoyoho_comp304sec003_lab02.data.Status
import com.example.yiuyiuyoyoho_comp304sec003_lab02.data.Task
import com.example.yiuyiuyoyoho_comp304sec003_lab02.viewmodel.TasksViewModel
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate


@Composable
fun TaskList(modifier: Modifier) {
    val tasksViewModel: TasksViewModel = koinViewModel()
    LazyColumn(
        modifier = modifier
    ) {
        items(tasksViewModel.getTasks()) { task ->
            TaskItem(task = task)
        }
    }

}

@Composable
fun TaskItem(task: Task) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
        ) {
            // Title
            Text(
                text = task.title,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black
            )
            // Truncated content preview
            Text(
                text = task.status.name,
                style = MaterialTheme.typography.titleSmall,
                color = Color.Black,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTaskList() {

    TaskItem(Task(1, "Sample", "This is a sample.", LocalDate.of(2024, 10, 11), Status.NEW))
}