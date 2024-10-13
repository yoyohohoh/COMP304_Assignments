package com.example.yiuyiuyoyoho_comp304sec003_lab02.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.yiuyiuyoyoho_comp304sec003_lab02.viewmodel.TasksViewModel
import com.example.yiuyiuyoyoho_comp304sec003_lab02.views.TasksBottomNavigation
import com.example.yiuyiuyoyoho_comp304sec003_lab02.views.TasksNavigationRail

@Composable
fun AppNavigationContent(
    tasksViewModel: TasksViewModel,
    contentType: ContentType,
    navigationType: NavigationType,
    onHomeClicked: () -> Unit,
    onOpenTaskClicked: () -> Unit,
    onClosedTaskClicked: () -> Unit,
    navHostController: NavHostController,
    onDrawerClicked: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        AnimatedVisibility(
            visible = navigationType == NavigationType.NavigationRail
        ) {
            TasksNavigationRail(
                onHomeClicked = onHomeClicked,
                onOpenTaskClicked = onOpenTaskClicked,
                onClosedTaskClicked = onClosedTaskClicked,
                onDrawerClicked = onDrawerClicked
            )
        }
        Scaffold(
            content = { paddingValues ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    AppNavigation(
                        tasksViewModel = tasksViewModel,
                        contentType = contentType,
                        navHostController = navHostController
                    )
                }
            },
            bottomBar = {
                AnimatedVisibility(
                    visible = navigationType == NavigationType.BottomNavigation
                ) {
                    TasksBottomNavigation(
                        onHomeClicked = onHomeClicked,
                        onOpenTaskClicked = onOpenTaskClicked,
                        onClosedTaskClicked = onClosedTaskClicked,
                    )
                }
            }
        )
    }
}


