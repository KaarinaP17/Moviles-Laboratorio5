package com.karinaPerez.laboratorio5.data

import android.os.Build
import androidx.annotation.RequiresApi
import java.util.Date

data class Task(
    val id: Int,
    val title: String,
    val description: String = "",
    val endDate: Date = Date(),
    val isCompleted: Boolean
)

@RequiresApi(Build.VERSION_CODES.O)
fun dateToString(date: Date): String {
    return "${date.month}/${date.day}/${date.year}";
}