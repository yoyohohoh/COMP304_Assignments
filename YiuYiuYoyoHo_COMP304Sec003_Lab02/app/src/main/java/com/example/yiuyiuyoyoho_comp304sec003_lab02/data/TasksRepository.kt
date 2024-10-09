package com.example.yiuyiuyoyoho_comp304sec003_lab02.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

interface TasksRepository {
    fun loadTasks(): Flow<List<Task>>
    fun addTask(task: Task)
    fun updateTask(task: Task)
}