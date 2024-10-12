package com.example.yiuyiuyoyoho_comp304sec003_lab02.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.yiuyiuyoyoho_comp304sec003_lab02.navigation.Activities

@Composable
fun TasksBottonNavigation(
    onHomeClicked: () -> Unit,
    onOpenTaskClicked: () -> Unit,
    onClosedTaskClicked: () -> Unit
){
    val items = listOf(Activities.HomeActivity, Activities.OpenTaskActivity, Activities.ClosedTaskActivity)
    val selectedItem = remember { mutableStateOf(items[0]) }

    NavigationBar(
        modifier = Modifier
            .fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.background
    ) {
        NavigationBarItem(
            selected = selectedItem.value == Activities.HomeActivity,
            onClick = {
                onHomeClicked()
                selectedItem.value = Activities.HomeActivity
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home"
                )
            }
        )

        NavigationBarItem(
            selected = selectedItem.value == Activities.OpenTaskActivity,
            onClick = {
                onOpenTaskClicked()
                selectedItem.value = Activities.HomeActivity
            },
            icon = {
                Icon(
                    imageVector = Icons.Filled.Warning,
                    contentDescription = "Open Task"
                )
            }
        )

        NavigationBarItem(
            selected = selectedItem.value == Activities.ClosedTaskActivity,
            onClick = {
                onClosedTaskClicked()
                selectedItem.value = Activities.HomeActivity
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = "Close Task"
                )
            }
        )
    }
}

@Preview
@Composable
fun PreviewBottom(){
    TasksBottonNavigation(onHomeClicked  = {}, onOpenTaskClicked  = {}, onClosedTaskClicked  = {})
}