package com.example.ukk2025

data class Task(
    val id: Int,
    val title: String,
    val description: String?,
    val dueDate: Long?,
    val completed: Boolean,
    val categoryId: Int
)