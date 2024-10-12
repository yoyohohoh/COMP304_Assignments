package com.example.yiuyiuyoyoho_comp304sec003_lab02.navigation

sealed class Activities (val route: String){
    object HomeActivity : Activities("home")
    object CreateTaskActivity : Activities("createTask")
    object ViewTaskActivity : Activities("viewTask/{taskID}")
    object EditTaskActivity : Activities("editTask/{taskID}")
    object OpenTaskActivity: Activities("openTask")
    object ClosedTaskActivity: Activities("closedTask")
}