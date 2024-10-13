package com.example.yiuyiuyoyoho_comp304sec003_lab02.navigation;

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.example.yiuyiuyoyoho_comp304sec003_lab02.ui.theme.YiuYiuYoyoHo_COMP304Sec003_Lab02Theme
import com.example.yiuyiuyoyoho_comp304sec003_lab02.viewmodel.TasksViewModel
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.yiuyiuyoyoho_comp304sec003_lab02.navigation.Activities
import com.example.yiuyiuyoyoho_comp304sec003_lab02.views.CreateTaskActivity
import com.example.yiuyiuyoyoho_comp304sec003_lab02.views.EditTaskActivity
import com.example.yiuyiuyoyoho_comp304sec003_lab02.views.HomeActivity
import com.example.yiuyiuyoyoho_comp304sec003_lab02.views.ViewTaskActivity
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import com.example.yiuyiuyoyoho_comp304sec003_lab02.data.Status

@Composable
fun AppNavigation(
    tasksViewModel: TasksViewModel,
    contentType: ContentType,
    navHostController: NavHostController = rememberNavController()
) {
    var _taskID: Int = -1
    val selectedItem = remember { mutableStateOf<Activities>(Activities.HomeActivity) }

    NavHost(navController = navHostController, startDestination = Activities.HomeActivity.route) {
        composable(Activities.HomeActivity.route) {
            HomeActivity(
                contentType = contentType,
                tasksViewModel = tasksViewModel,
                navigationToViewActivity = {task ->
                    navHostController.navigate(
                        "${Activities.ViewTaskActivity.route}/${task.id}")
                    _taskID = task.id
                },
                navigationToCreateActivity = {
                    navHostController.navigate(Activities.CreateTaskActivity.route)
                },
                filter = listOf(Status.PENDING, Status.NEW, Status.CLOSED)

            )
        }

        composable(route = "${Activities.ViewTaskActivity.route}/{taskID}") {
            val taskID = _taskID
            val currentTask = tasksViewModel.getTaskByID(taskID)

            if (currentTask != null) {
                ViewTaskActivity(
                    task = currentTask,
                    navigationToHomeActivity = {
                        navHostController.navigate(Activities.HomeActivity.route)
                    },
                    navigationToEditActivity = {
                        navHostController.navigate("${Activities.EditTaskActivity.route}/$taskID")
                    }
                )
            } else {
                Log.d("Main Activity", "ViewTaskActivity Failed")
            }

        }

        composable(route = "${Activities.EditTaskActivity.route}/{taskID}") {
            val taskID = _taskID
            val currentTask = tasksViewModel.getTaskByID(taskID)

            if (currentTask != null) {
                EditTaskActivity(
                    task = currentTask,
                    navigationToHomeActivity = {
                        navHostController.navigate(Activities.HomeActivity.route)
                    },
                    tasksViewModel = tasksViewModel
                )
            } else {
                Log.d("Main Activity", "EditTaskActivity Failed")
            }

        }

        composable(Activities.CreateTaskActivity.route) {
            CreateTaskActivity(
                navigationToHomeActivity = {
                    navHostController.navigate(Activities.HomeActivity.route)
                },
                tasksViewModel = tasksViewModel
            )

        }

        composable(Activities.OpenTaskActivity.route) {
            HomeActivity(
                contentType = ContentType.List,
                tasksViewModel = tasksViewModel,
                navigationToViewActivity = {task ->
                    navHostController.navigate(
                        "${Activities.ViewTaskActivity.route}/${task.id}")
                    _taskID = task.id
                },
                navigationToCreateActivity = {
                    navHostController.navigate(Activities.CreateTaskActivity.route)
                }, filter = listOf(Status.PENDING, Status.NEW)

            )
        }

        composable(Activities.ClosedTaskActivity.route) {
            HomeActivity(
                contentType = ContentType.List,
                tasksViewModel = tasksViewModel,
                navigationToViewActivity = {task ->
                    navHostController.navigate(
                        "${Activities.ViewTaskActivity.route}/${task.id}")
                    _taskID = task.id
                },
                navigationToCreateActivity = {
                    navHostController.navigate(Activities.CreateTaskActivity.route)
                }, filter = listOf(Status.CLOSED)

            )
        }
    }
}




