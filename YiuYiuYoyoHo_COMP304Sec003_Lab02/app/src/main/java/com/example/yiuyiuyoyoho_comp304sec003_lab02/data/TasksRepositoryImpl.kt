package com.example.yiuyiuyoyoho_comp304sec003_lab02.data

import java.time.LocalDate

class TasksRepositoryImpl : TasksRepository {
    private val tasks = mutableListOf<Task>() // Mutable list to store tasks

    init {
        tasks.addAll(
            listOf(
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
        )
    }

    override fun getTasks(): List<Task> {
        return tasks
    }

    override fun addTask(task: Task) {
        tasks.add(task)
    }

    override fun updateTask(updatedTask: Task) {
        val index = tasks.indexOfFirst { it.id == updatedTask.id }
        if (index != -1) {
            tasks[index] = updatedTask
        }
    }
}