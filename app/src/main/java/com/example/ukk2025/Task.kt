package com.example.ukk2025

data class Task(
    var id: String = "",
    val title: String = "",
    val description: String? = null,
    val dueDate: Long? = null,
    val completed: Boolean = false,
    val categoryId: Int = 0
)
