package com.example.yiuyiuyoyoho_comp304sec003_lab02.data

interface TasksRepository {
    fun getTasks(): List<Task>
    fun addTask(task: Task)
    fun updateTask(task: Task)
}