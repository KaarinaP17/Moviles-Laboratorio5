package com.karinaPerez.laboratorio5.views

import androidx.lifecycle.ViewModel
import com.karinaPerez.laboratorio5.data.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class GeneralViewModel : ViewModel() {
    private val _tasks = MutableStateFlow<MutableList<Task>>(mutableListOf())
    val tasks = _tasks.asStateFlow()

    fun addTask(task: Task) {
        _tasks.value = _tasks.value.toMutableList().apply { add(task) }
    }

    fun removeTask(id: Int) {
        _tasks.value = _tasks.value.toMutableList().apply {
            removeIf { it.id == id }
        }
    }

    fun changeStatus(id: Int) {
        _tasks.value = _tasks.value.map { task ->
            if (task.id == id) {
                task.copy(isCompleted = !task.isCompleted)
            } else {
                task
            }
        }.toMutableList()
    }
}