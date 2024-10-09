package com.example.yiuyiuyoyoho_comp304sec003_lab02.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.yiuyiuyoyoho_comp304sec003_lab02.data.Status
import com.example.yiuyiuyoyoho_comp304sec003_lab02.data.Task
import androidx.compose.material3.Text
import androidx.compose.ui.tooling.preview.Preview
import java.time.LocalDate

class TaskDetailsScreen {

}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TaskDetailsScreenContent(task: Task) {
    Column(

    ) {
        // Title
        Text(
            text = "Title: ${task.title}",
            style = MaterialTheme.typography.titleLarge
        )

        // Description
        Text(
            text = "Description: ${task.description}",
            style = MaterialTheme.typography.bodyMedium
        )

        // Due Date
        Text(
            text = "Due Date: ${task.dueDate}",
            style = MaterialTheme.typography.bodySmall
        )

        // Status
        FlowRow(
            modifier = Modifier
                .padding(start = 6.dp, end = 6.dp)
        ) {

            SuggestionChip(
                modifier = Modifier
                    .padding(start = 3.dp, end = 3.dp),
                onClick = { },
                label = {
                    Text(text = task.status.name)
                }
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTaskDetails() {
    TaskDetailsScreenContent(Task(1, "Sample", "This is a sample.", LocalDate.of(2024, 10, 11), Status.NEW))
}