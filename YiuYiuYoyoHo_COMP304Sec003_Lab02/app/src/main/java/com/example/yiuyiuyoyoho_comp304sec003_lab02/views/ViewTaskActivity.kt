package com.example.yiuyiuyoyoho_comp304sec003_lab02.views

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.yiuyiuyoyoho_comp304sec003_lab02.data.Status
import com.example.yiuyiuyoyoho_comp304sec003_lab02.data.Task
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat.startActivity
import com.example.yiuyiuyoyoho_comp304sec003_lab02.MainActivity
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewTaskActivity(task: Task, navigationToHomeActivity:() -> Unit, navigationToEditActivity:() -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Task Details")
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    IconButton(
                        onClick = {navigationToHomeActivity()},
                        modifier = Modifier.semantics { contentDescription = "Home" },
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
                onClick = {navigationToEditActivity()},
                modifier = Modifier.semantics { contentDescription = "Edit Task" }
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit"
                )
            }
        },

        content = { paddingValues ->
            // Apply padding only where necessary
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues)
                    .padding(16.dp) // Optional extra padding for content
            ) {
                TaskDetails(
                    task = task
                )
            }
        }
    )
}



