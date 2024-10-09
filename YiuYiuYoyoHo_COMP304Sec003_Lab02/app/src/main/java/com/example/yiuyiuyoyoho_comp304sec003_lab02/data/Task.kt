package com.example.yiuyiuyoyoho_comp304sec003_lab02.data

import java.time.LocalDate

enum class Status{
    NEW,
    PENDING,
    CLOSED
}

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val dueDate: LocalDate,
    val status: Status = Status.NEW
)




