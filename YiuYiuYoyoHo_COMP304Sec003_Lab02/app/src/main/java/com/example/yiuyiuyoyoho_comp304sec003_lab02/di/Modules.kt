package com.example.yiuyiuyoyoho_comp304sec003_lab02.di

import com.example.yiuyiuyoyoho_comp304sec003_lab02.data.TasksRepository
import com.example.yiuyiuyoyoho_comp304sec003_lab02.data.TasksRepositoryImpl
import com.example.yiuyiuyoyoho_comp304sec003_lab02.viewmodel.TasksViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {
    single<TasksRepository> { TasksRepositoryImpl() }
    single { TasksViewModel(get()) }
}