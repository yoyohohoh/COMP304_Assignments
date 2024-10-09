package com.example.yiuyiuyoyoho_comp304sec003_lab02.viewmodel

import androidx.lifecycle.ViewModel
import com.example.yiuyiuyoyoho_comp304sec003_lab02.data.TasksRepository

class TasksViewModel(
    private val tasksRepository: TasksRepository
): ViewModel() {

    fun getTasks() = tasksRepository.getTasks()
}