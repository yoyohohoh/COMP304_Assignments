package com.example.yiuyiuyoyoho_comp304sec003_lab02.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate


class TasksRepositoryImpl : TasksRepository {
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: Flow<List<Task>> = _tasks.asStateFlow()

    init{
    _tasks.value = mutableListOf(
                Task(
                    id = 1,
                    title = "Task 1",
                    description = "It is the first task.",
                    dueDate = LocalDate.of(2024, 10, 5),
                    status = Status.CLOSED
                ),
                Task(
                    id = 2,
                    title = "Task 2",
                    description = "It is the second task.",
                    dueDate = LocalDate.of(2024, 10, 11),
                    status = Status.PENDING
                ),
                Task(
                    id = 3,
                    title = "Task 3",
                    description = "It is the third task.",
                    dueDate = LocalDate.of(2024, 10, 13),
                    status = Status.NEW
                )
    )
    }


    override fun loadTasks(): Flow<List<Task>> {
        return tasks
    }

    override fun addTask(task: Task) {
        _tasks.value = _tasks.value.toMutableList().apply { add(task) }
    }

    override fun updateTask(updatedTask: Task) {
        val updatedTasks = _tasks.value.map {
            if (it.id == updatedTask.id) updatedTask else it
        }
        _tasks.value = updatedTasks // Emit the updated task list
    }


}