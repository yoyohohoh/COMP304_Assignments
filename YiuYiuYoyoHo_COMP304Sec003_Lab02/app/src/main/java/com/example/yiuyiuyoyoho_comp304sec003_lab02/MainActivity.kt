package com.example.yiuyiuyoyoho_comp304sec003_lab02

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.example.yiuyiuyoyoho_comp304sec003_lab02.ui.theme.YiuYiuYoyoHo_COMP304Sec003_Lab02Theme
import com.example.yiuyiuyoyoho_comp304sec003_lab02.viewmodel.TasksViewModel
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.yiuyiuyoyoho_comp304sec003_lab02.navigation.Activities
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.window.layout.FoldingFeature
import androidx.window.layout.WindowInfoTracker
import com.example.yiuyiuyoyoho_comp304sec003_lab02.navigation.ContentType
import com.example.yiuyiuyoyoho_comp304sec003_lab02.navigation.NavigationType
import com.example.yiuyiuyoyoho_comp304sec003_lab02.navigation.AppNavigationContent
import com.example.yiuyiuyoyoho_comp304sec003_lab02.navigation.DeviceFoldPosture
import com.example.yiuyiuyoyoho_comp304sec003_lab02.navigation.isBookPosture
import com.example.yiuyiuyoyoho_comp304sec003_lab02.navigation.isSeparating
import com.example.yiuyiuyoyoho_comp304sec003_lab02.views.TasksNavigationDrawer
import com.example.yiuyiuyoyoho_comp304sec003_lab02.views.TasksNavigationRail
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val tasksViewModel: TasksViewModel by viewModels()
    private var _taskID: Int = 0
    //val mockTask = Task(1, "Sample Task", "This is a sample description. This task is for sample preview only.", LocalDate.of(2024, 10, 11), Status.NEW)

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val deviceFoldingPostureFlow = WindowInfoTracker.getOrCreate(this).windowLayoutInfo(this)
            .flowWithLifecycle(this.lifecycle)
            .map { layoutInfo ->
                val foldingFeature =
                    layoutInfo.displayFeatures
                        .filterIsInstance<FoldingFeature>()
                        .firstOrNull()
                when {
                    isBookPosture(foldingFeature) ->
                        DeviceFoldPosture.BookPosture(foldingFeature.bounds)

                    isSeparating(foldingFeature) ->
                        DeviceFoldPosture.SeparatingPosture(
                            foldingFeature.bounds,
                            foldingFeature.orientation
                        )

                    else -> DeviceFoldPosture.NormalPosture
                }
            }
            .stateIn(
                scope = lifecycleScope,
                started = SharingStarted.Eagerly,
                initialValue = DeviceFoldPosture.NormalPosture
            )
        setContent {
            val navHostController = rememberNavController()
            val windowSizeClass = calculateWindowSizeClass(activity = this)
            val devicePosture = deviceFoldingPostureFlow.collectAsStateWithLifecycle().value
            val scope = rememberCoroutineScope()
            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            YiuYiuYoyoHo_COMP304Sec003_Lab02Theme {
                val navigationType: NavigationType
                val contentType: ContentType
                when (windowSizeClass.widthSizeClass) {
                    WindowWidthSizeClass.Compact -> {
                        navigationType = NavigationType.BottomNavigation
                        contentType = ContentType.List
                    }

                    WindowWidthSizeClass.Medium -> {
                        navigationType = NavigationType.NavigationRail
                        contentType = if (devicePosture is DeviceFoldPosture.BookPosture
                            || devicePosture is DeviceFoldPosture.SeparatingPosture
                        ) {
                            ContentType.ListAndDetail
                        } else {
                            ContentType.List
                        }
                    }

                    WindowWidthSizeClass.Expanded -> {
                        navigationType = if (devicePosture is DeviceFoldPosture.BookPosture) {
                            NavigationType.NavigationRail
                        } else {
                            NavigationType.NavigationDrawer
                        }
                        contentType = ContentType.ListAndDetail
                    }

                    else -> {
                        navigationType = NavigationType.BottomNavigation
                        contentType = ContentType.List
                    }
                }
                if (navigationType == NavigationType.NavigationDrawer) {
                    PermanentNavigationDrawer(
                        drawerContent = {
                            PermanentDrawerSheet {
                                TasksNavigationDrawer(
                                    onHomeClicked = {
                                        navHostController.navigate(Activities.HomeActivity.route)
                                    },
                                    onOpenTaskClicked = {
                                        navHostController.navigate(Activities.OpenTaskActivity.route)
                                    },
                                    onClosedTaskClicked = {
                                        navHostController.navigate(Activities.ClosedTaskActivity.route)
                                    },
                                    onDrawerClicked = {
                                        scope.launch {
                                            drawerState.close()
                                        }
                                    }

                                )
                            }
                        }
                    ) {
                        AppNavigationContent(
                            tasksViewModel = tasksViewModel,
                            contentType = contentType,
                            navigationType = navigationType,
                            onHomeClicked = {
                                navHostController.navigate(Activities.HomeActivity.route)
                            },
                            onOpenTaskClicked = {
                                navHostController.navigate(Activities.OpenTaskActivity.route)
                            },
                            onClosedTaskClicked = {
                                navHostController.navigate(Activities.ClosedTaskActivity.route)
                            },
                            navHostController = navHostController

                            )
                    }
                } else {
                    ModalNavigationDrawer(
                        drawerContent = {
                            ModalDrawerSheet {
                                TasksNavigationDrawer(
                                    onHomeClicked = {
                                        navHostController.navigate(Activities.HomeActivity.route)
                                    },
                                    onOpenTaskClicked = {
                                        navHostController.navigate(Activities.OpenTaskActivity.route)
                                    },
                                    onClosedTaskClicked = {
                                        navHostController.navigate(Activities.ClosedTaskActivity.route)
                                    },
                                    onDrawerClicked = {
                                        scope.launch {
                                            drawerState.close()
                                        }
                                    }
                                )
                            }
                        },
                        drawerState = drawerState
                    ) {
                        AppNavigationContent(

                            tasksViewModel = tasksViewModel,
                            contentType = contentType,
                            navigationType = navigationType,
                            onHomeClicked = {
                                navHostController.navigate(Activities.HomeActivity.route)
                            },
                            onOpenTaskClicked = {
                                navHostController.navigate(Activities.OpenTaskActivity.route)
                            },
                            onClosedTaskClicked = {
                                navHostController.navigate(Activities.ClosedTaskActivity.route)
                            },
                            navHostController = navHostController,
                            onDrawerClicked = {
                                scope.launch {
                                    drawerState.open()
                                }
                            }
                        )
                    }

                }
            }
        }
    }
}