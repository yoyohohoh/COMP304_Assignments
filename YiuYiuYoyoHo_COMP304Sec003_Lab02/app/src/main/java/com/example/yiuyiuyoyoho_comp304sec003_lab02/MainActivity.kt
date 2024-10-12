package com.example.yiuyiuyoyoho_comp304sec003_lab02

import android.os.Bundle
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

class MainActivity : ComponentActivity() {
    private val tasksViewModel: TasksViewModel by viewModels()
    private var _taskID: Int = 0
    //val mockTask = Task(1, "Sample Task", "This is a sample description. This task is for sample preview only.", LocalDate.of(2024, 10, 11), Status.NEW)

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            YiuYiuYoyoHo_COMP304Sec003_Lab02Theme {
                MainScreen()
                val windowSizeClass = calculateWindowSizeClass(this)
            }
        }
    }
    
    @Composable
    fun MyApp(windowSizeClass: WindowSizeClass){
        when (windowSizeClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> {
                Text("This is a compact layout")
            }

            WindowWidthSizeClass.Medium -> {
                Text("This is a medium layout")
            }

            WindowWidthSizeClass.Expanded -> {
                Text("This is a expanded layout")
            }

            else -> {
                Text("This is a default layout")
            }
        }

    }

    @Composable
    fun MainScreen() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = Activities.HomeActivity.route) {
            composable(Activities.HomeActivity.route) {
                HomeActivity(
                    tasksViewModel = tasksViewModel,
                    navigationToViewActivity = {task ->
                        navController.navigate(
                        "${Activities.ViewTaskActivity.route}/${task.id}")
                        _taskID = task.id
                    },
                            navigationToCreateActivity = {
                        navController.navigate(Activities.CreateTaskActivity.route)
                    }

                )
            }

            composable(route = "${Activities.ViewTaskActivity.route}/{taskID}") {
                val taskID = _taskID
                val currentTask = tasksViewModel.getTaskByID(taskID)

                if (currentTask != null) {
                    ViewTaskActivity(
                        task = currentTask,
                        navigationToHomeActivity = {
                            navController.navigate(Activities.HomeActivity.route)
                        },
                        navigationToEditActivity = {
                            navController.navigate("${Activities.EditTaskActivity.route}/$taskID")
                        }
                    )
                }
                else{
                    Log.d("Main Activity", "ViewTaskActivity Failed")
                }

            }

            composable(route = "${Activities.EditTaskActivity.route}/{taskID}") {
                val taskID =_taskID
                val currentTask = tasksViewModel.getTaskByID(taskID)

                if (currentTask != null) {
                    EditTaskActivity(
                        task = currentTask,
                        navigationToHomeActivity = {
                            navController.navigate(Activities.HomeActivity.route)
                        },
                        tasksViewModel = tasksViewModel
                    )
                }
                else{
                    Log.d("Main Activity", "EditTaskActivity Failed")
                }

            }

            composable(Activities.CreateTaskActivity.route) {
                CreateTaskActivity(
                    navigationToHomeActivity = {
                        navController.navigate(Activities.HomeActivity.route)
                    },
                    tasksViewModel = tasksViewModel
                )

            }

            composable(Activities.OpenTaskActivity.route) {

            }

            composable(Activities.ClosedTaskActivity.route) {

            }



    }
}
}