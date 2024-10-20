package com.example.todolist

data class TodoItem (
    val title: String,
    var isSelected: Boolean = false
)