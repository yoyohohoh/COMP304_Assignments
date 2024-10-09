package com.example.yiuyiuyoyoho_comp304sec003_lab02.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yiuyiuyoyoho_comp304sec003_lab02.data.Status
import com.example.yiuyiuyoyoho_comp304sec003_lab02.data.Task
import com.example.yiuyiuyoyoho_comp304sec003_lab02.data.TasksRepository
import com.example.yiuyiuyoyoho_comp304sec003_lab02.data.TasksRepositoryImpl

import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TasksViewModel: ViewModel() {
    private val repository = TasksRepositoryImpl()

    val tasks: StateFlow<List<Task>> = repository.getTasks()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addTask(task: Task) {
        viewModelScope.launch {
            repository.addTask(task)
        }
    }

    fun updateTask(updatedTask: Task) {
        viewModelScope.launch {
            repository.updateTask(updatedTask)
        }
    }

    fun updateTaskStatus(task: Task, newStatus: Status) {
        val updatedTask = task.copy(status = newStatus)
        Log.d("TasksViewModel", "Updating task: ${task.id} to status: ${newStatus.name}")
        viewModelScope.launch {
            repository.updateTask(updatedTask)
        }
    }
}