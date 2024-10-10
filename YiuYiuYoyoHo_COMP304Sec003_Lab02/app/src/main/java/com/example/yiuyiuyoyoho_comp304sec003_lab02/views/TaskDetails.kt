package com.example.yiuyiuyoyoho_comp304sec003_lab02.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yiuyiuyoyoho_comp304sec003_lab02.data.Status
import com.example.yiuyiuyoyoho_comp304sec003_lab02.data.Task
import java.time.LocalDate


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TaskDetails(task: Task) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        // Title
        Text(
            text = "Title: ${task.title}",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 8.dp) // Space between elements
        )

        // Description
        Text(
            text = "Description: ${task.description}",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Due Date
        Text(
            text = "Due Date: ${task.dueDate}",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(bottom = 8.dp)
        )

// Add a Spacer to push the status to the bottom
        Spacer(modifier = Modifier.height(6.dp))

        // Status text aligned to the bottom right
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End // Align to the right
        ) {
            Text(
                text = task.status.name,
                fontSize = 18.sp,
                modifier = Modifier
                    .background(
                        color = when (task.status) {
                            Status.NEW -> Color(0xFF89e18c)
                            Status.PENDING -> Color(0xFFcb5f5f)
                            Status.CLOSED -> Color.Gray
                        },
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(all = 6.dp)
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun TaskDetailsPreview() {
    TaskDetails(
        task = Task(1, "Sample Task", "This is a sample description. This task is for sample preview only.", LocalDate.of(2024, 10, 11), Status.NEW)
    )
}

