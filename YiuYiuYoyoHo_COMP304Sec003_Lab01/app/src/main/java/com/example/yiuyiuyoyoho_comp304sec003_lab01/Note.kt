package com.example.yiuyiuyoyoho_comp304sec003_lab01

data class Note(
    val title: String,
    val content: String
)
val notesList = mutableListOf<Note>(
    Note("First Note", "First things first, I'ma say all the words inside my head"),
    Note("Second Note", "Second thing second, don't you tell me what you think that I could be"),
    Note("Third Note", "Last things last, by the grace of fire and flames")
)