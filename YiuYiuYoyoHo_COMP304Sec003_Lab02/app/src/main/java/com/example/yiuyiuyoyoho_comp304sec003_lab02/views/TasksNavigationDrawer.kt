package com.example.yiuyiuyoyoho_comp304sec003_lab02.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.yiuyiuyoyoho_comp304sec003_lab02.navigation.Activities

@Composable
fun TasksNavigationDrawer(
    onHomeClicked: () -> Unit,
    onOpenTaskClicked: () -> Unit,
    onClosedTaskClicked: () -> Unit,
    onDrawerClicked: () -> Unit = {}
){
    val items = listOf(Activities.HomeActivity, Activities.OpenTaskActivity, Activities.ClosedTaskActivity)
    val selectedItem = remember { mutableStateOf(items[0]) }

    Column(
        modifier = Modifier
            .wrapContentWidth()
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.inverseOnSurface)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "My Tasks",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            IconButton(
                onClick = onDrawerClicked
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Navigation Drawer Icon"
                )
            }
        }
        NavigationDrawerItem(
            label = { Text(text = "Tasks List") },
            selected = false,
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

        NavigationDrawerItem(
            label = { Text(text = "Open Tasks") },
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

        NavigationDrawerItem(
            label = { Text(text = "Closed Task") },
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
fun PreviewDrawer(){
    TasksNavigationDrawer(onHomeClicked  = {}, onOpenTaskClicked  = {}, onClosedTaskClicked  = {}, onDrawerClicked={})
}