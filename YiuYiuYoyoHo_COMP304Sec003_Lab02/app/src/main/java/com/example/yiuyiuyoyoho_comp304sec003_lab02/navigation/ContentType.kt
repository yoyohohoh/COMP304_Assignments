package com.example.yiuyiuyoyoho_comp304sec003_lab02.navigation

sealed interface ContentType {
    object List : ContentType
    object ListAndDetail : ContentType
}