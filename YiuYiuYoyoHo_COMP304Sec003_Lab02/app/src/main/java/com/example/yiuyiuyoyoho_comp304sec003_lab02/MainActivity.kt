package com.example.yiuyiuyoyoho_comp304sec003_lab02

import android.os.Bundle
import android.util.Log
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.example.yiuyiuyoyoho_comp304sec003_lab02.ui.theme.YiuYiuYoyoHo_COMP304Sec003_Lab02Theme
import com.example.yiuyiuyoyoho_comp304sec003_lab02.viewmodel.TasksViewModel
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.yiuyiuyoyoho_comp304sec003_lab02.data.Status
import com.example.yiuyiuyoyoho_comp304sec003_lab02.data.Task
import com.example.yiuyiuyoyoho_comp304sec003_lab02.navigation.Activities
import com.example.yiuyiuyoyoho_comp304sec003_lab02.views.CreateTaskActivity
import com.example.yiuyiuyoyoho_comp304sec003_lab02.views.EditTaskActivity
import com.example.yiuyiuyoyoho_comp304sec003_lab02.views.HomeActivity
import com.example.yiuyiuyoyoho_comp304sec003_lab02.views.ViewTaskActivity
import java.time.LocalDate

class MainActivity : ComponentActivity() {
    val tasksViewModel: TasksViewModel by viewModels()
    var _taskID: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            YiuYiuYoyoHo_COMP304Sec003_Lab02Theme {
                MainScreen()

            }
        }
    }

    @Composable
    fun MainScreen() {
        val navController = rememberNavController()
        val mockTask = Task(1, "Sample Task", "This is a sample description. This task is for sample preview only.", LocalDate.of(2024, 10, 11), Status.NEW)
        NavHost(navController = navController, startDestination = Activities.HomeActivity.route) {
            composable(Activities.HomeActivity.route) {
                HomeActivity(
                    tasksViewModel = tasksViewModel,
                    navigationToViewActivity = {task ->
                        navController.navigate(
                        "${Activities.ViewTaskActivity.route}/${task.id}")
                        _taskID = task.id
                        Log.d("Main Home Call", "I am Calling ${_taskID}")
                    },
                            navigationToCreateActivity = {
                        navController.navigate("${Activities.CreateTaskActivity.route}")
                    }

                )
            }

            composable(route = "${Activities.ViewTaskActivity.route}/{taskID}") {
//                    backStackEntry ->
//                val taskID = backStackEntry.arguments?.getString("taskID")?.toIntOrNull() ?: 0
                Log.d("Main View Call", "I am Calling ${_taskID}")
                val taskID = _taskID
                //Log.d("MainActivity", "Retrieved taskID: $taskID")
                val currentTask = tasksViewModel.getTaskByID(taskID)

                if (currentTask != null) {
                    ViewTaskActivity(
                        task = currentTask,
                        navigationToHomeActivity = {
                            navController.navigate("${Activities.HomeActivity.route}")
                        },
                        navigationToEditActivity = {
                            navController.navigate("${Activities.EditTaskActivity.route}/$taskID")
                        }
                    )
                }
                else{
                    Log.d("Get ID", "Failed getting ID")
                }

            }

            composable(route = "${Activities.EditTaskActivity.route}/{taskID}") {
                val taskID =_taskID
                val currentTask = tasksViewModel.getTaskByID(taskID)

                if (currentTask != null) {
                    EditTaskActivity(
                        task = currentTask,
                        navigationToHomeActivity = {
                            navController.navigate("${Activities.HomeActivity.route}")
                        },
                        tasksViewModel = tasksViewModel
                    )
                }
                else{
                    Log.d("Get ID", "Failed getting ID")
                }

            }

            composable(Activities.CreateTaskActivity.route) {
                CreateTaskActivity(
                    navigationToHomeActivity = {
                        navController.navigate("${Activities.HomeActivity.route}")
                    },
                    tasksViewModel = tasksViewModel
                )

            }



    }
}
}